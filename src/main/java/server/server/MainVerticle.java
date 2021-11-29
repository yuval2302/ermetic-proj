package server.server;

import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import server.server.httpserver.HttpServer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import com.google.inject.Inject;

public class MainVerticle extends AbstractVerticle {
    @Inject
    private HttpServer httpServer;
    @Inject
    private Vertx vertx;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        httpServer.createHttpServer()
                .onSuccess(result -> {
                    startPromise.complete();
                })
                .onFailure(throwable -> {
                    logger.error(throwable);
                    startPromise.fail(throwable);
                });
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        super.stop(stopPromise);
    }
}

