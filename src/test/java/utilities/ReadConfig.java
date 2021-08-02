package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    Properties properties;

    public ReadConfig(){
        File src = new File("./Configuration/config.properties");
        try{
            FileInputStream fileInputStream = new FileInputStream(src);
            properties = new Properties();
            properties.load(fileInputStream);

        }catch (Exception e){
            throw new RuntimeException("Error while reading the config file");
        }
    }

    public String getApplicationURL(){
       String base_Url = properties.getProperty("baseURL");
        return base_Url;
    }

    public String getChromeDriverPath(){
        String path_chromeDriver = properties.getProperty("chromeDriverPath");
        return path_chromeDriver;
    }


}

