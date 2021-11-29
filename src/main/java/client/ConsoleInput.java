package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
    public int getNumberOfClients() {
        int numOfClients=0;
        do {
            System.out.println("please enter a valid number of httpclient instances desired.");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                String valueFromUser = reader.readLine();
                numOfClients = Integer.parseInt(valueFromUser);
            } catch (Exception ex) {
                System.out.println("Wrong input please try again.");
            }
        } while (numOfClients<=0);

        return numOfClients;
    }

    public void waitForAnyUserKey() throws IOException{
        System.out.println("Waiting For key to terminate");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String valueFromUser = reader.readLine();
    }
}
