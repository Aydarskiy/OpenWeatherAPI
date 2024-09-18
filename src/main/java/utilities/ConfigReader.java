package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ConfigReader {

    /**
     * This class reads Configuration.properties file.
     * The method getProperty(String key); fetches the
     * values from properties file using the key
     * provided as a parameter.
     */

   private static FileInputStream input;
   private static Properties properties;


    static {
        String path = System.getProperty("user.dir") + "/src/main/resources/configurations/Configuration.properties";
        try {
            input = new FileInputStream(path); // String needs to be opened to connect a file
            properties = new Properties();     // properties needed to load a file
            properties.load(input);            // file is being loaded
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is invalid");   // if the file is not found or path corrupted
        } catch (IOException e) {
            System.out.println("Failed to load properties file");        // if the file is not loaded properly
        }finally {
            try {
                assert input != null;
                input.close();
            } catch (IOException e) {
                System.out.println("Exception occurred when trying to close the input object");   // if input can't be closed
            }
        }
    }

    /**
     * This method accepts the key as String and returns the value as a String.
     */
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
