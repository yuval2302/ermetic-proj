package client.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.Router;
import server.server.MainVerticle;
import server.server.ServiceStarter;
import server.server.ServiceStarterImpl;
import server.server.httpserver.HttpRequestHandler;
import server.server.httpserver.HttpServer;
import server.server.httpserver.HttpServerImpl;
import server.syncmanager.ClientRequestsSyncManager;
import server.syncmanager.SyncManager;
import server.utils.PropertiesProvider;
import server.utils.PropertiesProviderImpl;

public class VertxModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PropertiesProvider.class).to(PropertiesProviderImpl.class).asEagerSingleton();
        bind(ServiceStarter.class).to(ServiceStarterImpl.class);
        bind(HttpRequestHandler.class).toInstance(new HttpRequestHandler());
        bind(HttpServer.class).to(HttpServerImpl.class).asEagerSingleton();
        bind(SyncManager.class).to(ClientRequestsSyncManager.class).asEagerSingleton();
        bind(MainVerticle.class).toInstance(new MainVerticle());
    }
}
