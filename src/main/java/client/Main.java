package client;

import com.google.inject.Guice;
import server.modules.PropertiesModule;
import server.modules.VertxModule;
import server.server.ServiceStarter;

public class Main {
  public static void main(String[] args){
    Guice.createInjector(new VertxModule(), new PropertiesModule())
            .getInstance(ServiceStarter.class).start();
  }
}
