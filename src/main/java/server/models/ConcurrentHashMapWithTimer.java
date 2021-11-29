package server.models;

import io.vertx.core.Vertx;

import com.google.inject.Inject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ConcurrentHashMapWithTimer<T, M> implements MapWithTimer<T,M>{
    private Vertx vertx;
    private ConcurrentHashMap<T, M> concurrentHashMap;
    private long timeout;

    public ConcurrentHashMapWithTimer(Vertx vertx, long timeout) {
        this.concurrentHashMap = new ConcurrentHashMap<>();
        this.vertx=vertx;
        this.timeout = timeout;
    }

    @Override
    public void put(T key, M value) {
            if(concurrentHashMap.get(key)==null) {
                putTimerToKet(key);
            }
            concurrentHashMap.put(key, value);
    }

    private void putTimerToKet(T key) {
        vertx.setTimer(timeout, (timerId) -> this.remove(key));
    }

    @Override
    public M get(T key) {
        return concurrentHashMap.get(key);
    }

    @Override
    public M remove(T key) {
        return concurrentHashMap.remove(key);
    }

    public M computeIfAbsent(T key, M defaultValue) {
        return concurrentHashMap.computeIfAbsent(key, putKey-> {
            putTimerToKet(key);
            return defaultValue;
        });
    }

    public M computeIfPresent(T key, Function<M,M> mapping) {
        return concurrentHashMap.computeIfPresent(key, (putKey, value)-> mapping.apply(value));
    }
}
