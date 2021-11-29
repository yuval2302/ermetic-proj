package server.server.httpserver;

import io.vertx.core.Future;

public interface HttpServer {
    Future<Void> createHttpServer();


}
