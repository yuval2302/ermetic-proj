package client;

import client.modules.ClientModule;
import client.modules.PropertiesModule;
import com.google.inject.Guice;

import server.server.ServiceStarter;

public class Main {
  public static void main(String[] args){
    Guice.createInjector(new ClientModule(), new PropertiesModule())
            .getInstance(ClientStarter.class).start();
  }
}
