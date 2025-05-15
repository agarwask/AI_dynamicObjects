package pages.ai;

import org.openqa.selenium.WebDriver;
import utilities.LocatorReader;
import java.io.*;
import java.lang.Thread;

public class LoginPage_google {

    private WebDriver driver;

    public LoginPage_google(WebDriver driver, String pageJson){
        this.driver = driver;
        LocatorReader.loadLocatorsFromJson(pageJson);
    }


    public void performLogin(String userName, String password) throws InterruptedException {
//        driver.findElement(LocatorReader.findLocatorByPartialName("Sign In")).click();

        Thread.sleep(1000);

        driver.findElement(LocatorReader.findLocatorByPartialName("sort button")).click();
        Thread.sleep(2000);
        driver.findElement(LocatorReader.findLocatorByPartialName("WebView")).click();
//       driver.findElement(LocatorReader.findLocatorByPartialName("First name")).sendKeys(userName);
//        driver.findElement(LocatorReader.findLocatorByPartialName("Last name")).sendKeys(password);
//        driver.findElement(LocatorReader.findLocatorByPartialName("Password :")).sendKeys(password);
    }


}
