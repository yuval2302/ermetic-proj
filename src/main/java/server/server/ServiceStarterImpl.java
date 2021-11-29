package server.server;

import io.vertx.core.Vertx;

import com.google.inject.Inject;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceStarterImpl implements ServiceStarter {
    @Inject
    private Vertx vertx;
    @Inject
    private MainVerticle mainVerticle;
    @Inject
            private TerminateServer terminateServer;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start() {
        vertx.deployVerticle(mainVerticle).onFailure(e -> logger.error(e));
        Executors.newSingleThreadExecutor().execute(()->terminateServer.waitForKeyToTerminate());
    }
}
