package gettingStarted;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ai.*;
import utilities.LocatorReader;
import utilities.OpenAIHelper;


public class TestNGTest {

    private OpenAIClient client;
    private WebDriver driver;

    @BeforeTest
    public void Setup() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");

        client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        driver = new ChromeDriver();

        driver.navigate().to("https://www.synechron.com/");
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
    public void testLoginForUserFromAI() throws InterruptedException {
        System.out.println("Testing User Login from AI Locators");

        var jsonResponse = OpenAIHelper.GetLocatorsForPageAsJson(driver.getPageSource());

        LoginPage_google loginPage = new LoginPage_google(driver, jsonResponse);
//        loginPage.performLogin("admin", "password");
        loginPage.performLogin("agrawal_vsandeep@yahoo.co.in", "password");
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
