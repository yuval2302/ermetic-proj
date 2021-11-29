package server.models;

public class DosException extends Exception {
    public DosException(String clientIdentifier) {
        super("The client with client Id: " + clientIdentifier + " has been made too many request then the system can take.");
    }
}
