package client.utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesProviderImpl implements PropertiesProvider {
    private final Integer httpServerPort;
    private final Integer maxRandomTime;


    @Inject
    public PropertiesProviderImpl(@Named("HTTP_PORT") Integer httpServerPort,
                                  @Named("MAX_RANDOM_TIME") Integer maxRandomTime) {
        this.httpServerPort = httpServerPort;
        this.maxRandomTime = maxRandomTime;
    }

    @Override
    public int getHttpServerPort() {
        return this.httpServerPort;
    }

    @Override
    public int getMaxRandomTime() {
        return this.maxRandomTime;
    }

    private <T> T getOrDefault(T value, T def) {
        if(value==null) {
            return def;
        }
        return value;
    }
}
