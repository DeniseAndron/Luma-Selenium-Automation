package comLuma.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    //Create  an object for the properties files
    Properties props;

    //Constructor
    public ReadConfig () {
        //Declare the path of your config properties file
        File src = new File("./Configuration/config.properties");

        //We are reading the data from that props files using FileInputStream to open the file in read mode
        try {
            FileInputStream fis = new FileInputStream(src);
            props = new Properties();
            //At the runtime we will load the file using props.load
            props.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());

        }
    }

    //We read the value and then return it, we will use it  within our BaseClass
    public String getApplicationURL() {
        String url = props.getProperty("baseURL");
        return url;
    }

    public String getChromePath() {
        String chromepath = props.getProperty("chromepath");
        return chromepath;
    }

}
