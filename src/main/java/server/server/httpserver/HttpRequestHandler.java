package server.server.httpserver;

import com.google.inject.Inject;
import io.vertx.core.Future;
import server.models.DosException;
import server.syncmanager.ClientRequestsSyncManager;

public class HttpRequestHandler {
    @Inject
    private ClientRequestsSyncManager clientRequestsSyncManager;

    public Future<Void> handleClientRequest(String identifier) {
        Future<Void> future;
        if (!clientRequestsSyncManager.requestResource(identifier)) {
            future = Future.failedFuture(new DosException(identifier));
        } else {
            future = Future.succeededFuture();
        }
        return future;
    }
}
