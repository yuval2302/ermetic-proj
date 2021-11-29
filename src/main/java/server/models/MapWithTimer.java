package server.models;

import java.util.function.Function;

public interface MapWithTimer<T,M> {
    void put(T key, M value);
    M get(T key);
    M remove(T key);
    M computeIfAbsent(T key, M defaultValue);
    M computeIfPresent(T key, Function<M,M> Mapping);

}
