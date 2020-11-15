package pl.me.automation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {
    protected Properties properties = new Properties();

  {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        if (resourceAsStream != null) {
            try {
               properties.load(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found exception");
        }
        System.out.println("");
    }
}
