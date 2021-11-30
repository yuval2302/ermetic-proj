package server.utils;

import com.google.inject.Inject;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TerminateServer {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private Vertx vertx;
    public void waitForKeyToTerminate() {
        System.out.println("Waiting For key to terminate");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String valueFromUser = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exit();
    }
    private void exit() {
        vertx.close().onComplete(event -> System.exit(0));
    }
}
