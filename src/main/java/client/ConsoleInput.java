package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput {
    public int getNumberOfClients() throws IOException {
        int numOfClients=0;
        do {
            System.out.println("please enter a valid number of httpclient instances desired.");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            String valueFromUser = reader.readLine();
            try {
                numOfClients = Integer.parseInt(valueFromUser);
            } catch (NumberFormatException e) {
                System.out.println("Worng input [" + valueFromUser + "] please try again.");
            }
        } while (numOfClients<=0);

        return numOfClients;
    }

    public waitForAnyUserKey() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String valueFromUser = reader.readLine();

    }
}
