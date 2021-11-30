package client;

import client.utils.PropertiesProvider;
import com.google.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientStarter {
    @Inject
    private ConsoleInput consoleInput;
    @Inject
    private PropertiesProvider propertiesProvider;

    private ExecutorService executorService;

    private boolean isTerminated = false;

    public void start(){
        int numOfClients = consoleInput.getNumberOfClients();
        this.executorService = Executors.newFixedThreadPool(numOfClients);
        for(int i =0; i<numOfClients;i++) {
            executorService.execute(() -> makeServerRequest(numOfClients));
        }
        try {
            consoleInput.waitForAnyUserKey();
        } catch (Exception e){
            e.printStackTrace();
        }
        terminate();
    }

    public void makeServerRequest(int numOfClients) {
        int clientId = new Random().nextInt(numOfClients);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create("http://localhost:" + propertiesProvider.getHttpServerPort() + "?clientId=" + clientId))
                .header("accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(new Random().nextInt(propertiesProvider.getMaxRandomTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!this.isTerminated){
            this.makeServerRequest(numOfClients);
        }
    }

    private void terminate() {
        this.isTerminated=true;
        try {
            executorService.awaitTermination((long)propertiesProvider.getMaxRandomTime()*3,TimeUnit.MICROSECONDS);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }




}
