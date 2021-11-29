package server.server.httpserver;

import com.google.inject.Inject;
import io.vertx.core.Future;
import server.models.DosException;
import server.syncmanager.SyncManager;

public class HttpRequestHandler {
    @Inject
    private SyncManager syncManager;

    public Future<Void> handleClientRequest(String identifier) {
        Future<Void> future;
        if (!syncManager.requestResource(identifier)) {
            future = Future.failedFuture(new DosException(identifier));
        } else {
            future = Future.succeededFuture();
        }
        return future;
    }
}
