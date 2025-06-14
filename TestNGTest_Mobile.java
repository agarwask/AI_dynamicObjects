package gettingStarted;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ai.LoginPage_google;
import pages.ai.LoginPage_google1;
import utilities.OpenAIHelper;

import java.net.MalformedURLException;
import java.net.URL;


public class TestNGTest_Mobile {

    private OpenAIClient client;
    private WebDriver driver;
    private AndroidDriver androidDriver;

    @BeforeTest(enabled = false)
    public void Setup() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");

        // Initialize OpenAI client



//        client = OpenAIOkHttpClient.builder()
//                .apiKey(apiKey)
//                .build();



//        driver = new ChromeDriver();
//
//        driver.navigate().to("https://www.synechron.com/");
    }



//    @Test
//    public void testLogin() {
//
//        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
//                .addUserMessage("Get the page source from http://eaapp.somee.com and get all the locators like ID, Name, XPath and CSS")
//                .model(ChatModel.GPT_4_1)
//                .build();
//        ChatCompletion chatCompletion = client.chat().completions().create(params);
//
//        String message = chatCompletion.choices().get(0).message()._content().toString();
//
//        System.out.println("Response from GPT:" + message);
//    }

    @Test
    public void testLoginForUserFromAI() throws InterruptedException, MalformedURLException {
        System.out.println("Testing User Login from AI Locators");

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:app", "storage:filename=iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "iPhone Simulator");
        caps.setCapability("appium:platformVersion", "current_major");
        caps.setCapability("appium:automationName", "XCUITest");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", "oauth-shilpakhtn-1c645");
        sauceOptions.setCapability("accessKey", "30f7e1a3-bc57-4020-a651-994b7d22939b");
        sauceOptions.setCapability("build", "appium-build-VGTFG");
        sauceOptions.setCapability("name", "ios_test2345124");
        caps.setCapability("sauce:options", sauceOptions);

        // Start the session


        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        IOSDriver driver = new IOSDriver(url, caps);

//        Thread.sleep(5000);
//        AndroidDriver driver = new AndroidDriver(url, caps);
//        System.out.println(driver.getPageSource());
// replace with commands and assertions
        Thread.sleep(5000);
        var jsonResponse = OpenAIHelper.GetLocatorsForPageAsJson(driver.getPageSource());

        LoginPage_google loginPage = new LoginPage_google(driver, jsonResponse);
//        loginPage.performLogin("admin", "password");
        loginPage.loginMethod();

        Thread.sleep(5000);
        var jsonResponse1 = OpenAIHelper.GetLocatorsForPageAsJson(driver.getPageSource());

        LoginPage_google1 loginPage1 = new LoginPage_google1(driver, jsonResponse1);
//        loginPage.performLogin("admin", "password");
        loginPage1.loginMethod1();


//        String jobStatus = "passed"; // or "failed"

// end the session
//        driver.executeScript("sauce:job-result=" + jobStatus);

//        var jsonResponse = OpenAIHelper.GetLocatorsForPageAsJson(driver.getPageSource());
//
//        LoginPage_google loginPage = new LoginPage_google(driver, jsonResponse);
////        loginPage.performLogin("admin", "password");
//        loginPage.performLogin("agrawal_vsandeep@yahoo.co.in", "password");
    }

//    @Test
//    public void testLogoff() {
//        System.out.println("Testing the log off operation");
//    }
//
//    @Test
//    public void testSettings() {
//        System.out.println("Testing the settings operation");
//    }

}
