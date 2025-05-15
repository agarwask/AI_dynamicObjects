package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import utilities.models.Locator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocatorReader {

    private static Map<String, By> locatorMap = new HashMap<>();


    public static void loadLocatorsFromJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try{

            List<Locator> locators = objectMapper.readValue(jsonString, new TypeReference<List<Locator>>() {});

            for(Locator locator : locators){
                String key = locator.getLocator();
                String locatorName = locator.getLocatorName();
                String type = locator.getLocatorType().toLowerCase();
                By by;

                switch (type) {
                    case "id":
                        by = By.id(key);
                        break;
                    case "name":
                        by = By.name(key);
                        break;
                    case "css":
                        by = By.cssSelector(key);
                        break;
                    case "xpath":
                        by = By.xpath(key);
                        break;
                    case "linktext":
                        by = By.linkText(key);
                        break;
//                    case "accessibility id":
//                        by = By.accessibility id(key);
//                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported locator type: " + type);
                }

                locatorMap.put(locatorName, by);

            }

            System.out.println(locatorMap);
            try (FileWriter writer = new FileWriter("myFile.txt")) {
                writer.write(locatorMap.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }



    }


    public static By findLocatorByPartialName(String name) {
       for(Map.Entry<String, By> entry: locatorMap.entrySet()){
           if(entry.getKey().toLowerCase().contains(name.toLowerCase())){
               return entry.getValue();

           }
       }

       throw new NoSuchElementException("No locator found containing:" + name);
    }



}
