package server.utils;

public interface PropertiesProvider {
    int getHttpServerPort();
    long getRequestsFrameTime();
    int getMaxRequestsPerFrame();
}
