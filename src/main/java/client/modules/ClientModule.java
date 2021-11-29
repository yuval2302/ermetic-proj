package client.modules;

import client.ConsoleInput;
import client.utils.PropertiesProvider;
import client.utils.PropertiesProviderImpl;
import com.google.inject.AbstractModule;

public class ClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConsoleInput.class).asEagerSingleton();
        bind(PropertiesProvider.class).to(PropertiesProviderImpl.class);
    }
}
