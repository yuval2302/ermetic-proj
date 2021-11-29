package server.syncmanager;

public interface SyncManager<T> {
    boolean requestResource(T Identifier);
}
