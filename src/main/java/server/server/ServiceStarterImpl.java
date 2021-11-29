package server.server;

import io.vertx.core.Vertx;

import com.google.inject.Inject;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class ServiceStarterImpl implements ServiceStarter {
    @Inject
    private Vertx vertx;
    @Inject
    private MainVerticle mainVerticle;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start() {
        vertx.deployVerticle(mainVerticle).onFailure(e -> logger.error(e));
    }
}
