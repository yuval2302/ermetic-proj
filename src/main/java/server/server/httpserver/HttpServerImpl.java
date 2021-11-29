package server.server.httpserver;

import com.google.inject.Inject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import server.models.DosException;
import server.utils.PropertiesProvider;

public class HttpServerImpl implements HttpServer {
    private String Url = "/";
    @Inject
    private Vertx vertx;
    @Inject
    private Router router;
    @Inject
    private PropertiesProvider propertiesProvider;
    @Inject
    private HttpRequestHandler httpRequestHandler;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Future<Void> createHttpServer() {
        return vertx.createHttpServer().requestHandler(router)
                .listen(propertiesProvider.getHttpServerPort()).compose(httpServer -> {
                    logger.info("HTTP server started on port " + propertiesProvider.getHttpServerPort());
                    setServerRoutes();
                    return Future.succeededFuture();
                });
    }

    private void setServerRoutes() {
        router.post().handler(BodyHandler.create());

        router.get(Url).handler(routingContext -> {
            vertx.executeBlocking(promise -> {
                String clientId = routingContext.getBodyAsJson().getString("clientId");
                httpRequestHandler.handleClientRequest(clientId)
                        .onSuccess(event -> promise.complete())
                        .onFailure(promise::fail)
                ;
            }).onSuccess(unused -> {
                handleHttpResponse(routingContext, "", 200);
            }).onFailure(throwable -> {
                if(throwable instanceof DosException) {
                    handleHttpResponse(routingContext, throwable.getMessage(), HttpResponseStatus.SERVICE_UNAVAILABLE.code());
                } else {
                    handleHttpResponse(routingContext, throwable.getMessage(), HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
                }
            });
        });
    }

    private void handleHttpResponse(RoutingContext routingContext, String data, int statusCode) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("Content-Type", "application/json");
        response.putHeader("Content-Length", Integer.toString(data.length()));
        response.setStatusCode(statusCode);
        response.write(data)
                .onComplete(voidAsyncResult -> response.end())
                .onFailure(throwable -> {
                    logger.error(throwable);
                    response.setStatusCode(500).end();
                });
    }
}
