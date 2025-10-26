package helper;

import utils.LogUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/main/resources/config.properties";

    public static Properties loadAllFiles(){
        LinkedList<String> files = new LinkedList<>();
        files.add("src/main/resources/config.properties");

        try {
            properties = new Properties();
            for (String filePath : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + filePath;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            LogUtils.info("Properties loaded from multiple files: " + files);
            return properties;
        }catch (IOException ioe){
            return new Properties();
        }
    }
}
