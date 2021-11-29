package server.utils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesProviderImpl implements PropertiesProvider {
    private final Integer httpServerPort;
    private final long requestsFrameTime;
    private final Integer maxRequestsPerFrame;

    @Inject
    public PropertiesProviderImpl(@Named("HTTP_PORT") Integer httpServerPort,
                                  @Named("REQUESTS_FRAME_TIME") long requestsFrameTime,
                                  @Named("MAX_REQUESTS_PER_FRAME") Integer maxRequestsPerFrame) {
        this.httpServerPort = httpServerPort;
        this.requestsFrameTime = requestsFrameTime;
        this.maxRequestsPerFrame = maxRequestsPerFrame;
    }

    @Override
    public int getHttpServerPort() {
        return this.httpServerPort;
    }

    @Override
    public long getRequestsFrameTime() {
        return this.requestsFrameTime;
    }

    @Override
    public int getMaxRequestsPerFrame() {
        return this.maxRequestsPerFrame;
    }

    private <T> T getOrDefault(T value, T def) {
        if(value==null) {
            return def;
        }
        return value;
    }
}
