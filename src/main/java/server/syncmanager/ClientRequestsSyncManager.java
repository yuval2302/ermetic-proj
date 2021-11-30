package server.syncmanager;

import com.google.inject.Inject;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import server.models.ConcurrentHashMapWithTimer;
import server.models.MapWithTimer;
import server.utils.PropertiesProvider;
import java.util.function.Function;

public class ClientRequestsSyncManager {
    private static final Integer DEFAULT_VALUE_OF_NEW_KEY = 0;
    private final static Function<Integer, Integer> getNextValue = (Integer value) -> value+1;
    private final MapWithTimer<String, Integer> map;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private PropertiesProvider propertiesProvider;

    @Inject
    public ClientRequestsSyncManager(Vertx vertx, PropertiesProvider propertiesProvider) {
        this.map = new ConcurrentHashMapWithTimer<>(vertx, propertiesProvider.getRequestsFrameTime());
        this.propertiesProvider = propertiesProvider;
    }

    public boolean requestResource(String identifier) {
        boolean isResourceFree = true;
        map.computeIfAbsent(identifier, DEFAULT_VALUE_OF_NEW_KEY);
        Integer updatedValue = map.computeIfPresent(identifier,getNextValue);
        if(updatedValue!=null) {
            if(updatedValue > propertiesProvider.getMaxRequestsPerFrame()) {
                isResourceFree = false;
            }
        } else {
            isResourceFree = false;
        }
        logger.info("[" + identifier + "," + map.get(identifier) + "]");
        return isResourceFree;
    }
}
