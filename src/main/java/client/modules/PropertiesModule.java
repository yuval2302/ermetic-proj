package client.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {
    private final String propertiesFilePath = "src/main/resources/app.properties";
    @Override
    protected void configure() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFilePath));
            Names.bindProperties(binder(), properties);
        } catch (FileNotFoundException e) {
            System.out.println("The configuration file Test.properties can not be found");
        } catch (IOException e) {
            System.out.println("I/O Exception during loading configuration");
        }
    }
}
