package pages.ai;

import org.openqa.selenium.WebDriver;
import utilities.ExcelTestStepReader;
import utilities.LocatorReader;
import java.util.*;

public class LoginPage_google {
    private WebDriver driver;
    private Map<String, List<TestStep>> methodSteps;

    public LoginPage_google(WebDriver driver, String pageJson) {
//    public LoginPage_google(string pageJson) {
        this.driver = driver;
        LocatorReader.loadLocatorsFromJson(pageJson);
        // Load test steps from Excel
        this.methodSteps = ExcelTestStepReader.readTestSteps("src/test/resources/TestSteps.xlsx","LoginPage_google");
    }

    public void loginMethod() throws InterruptedException {
        List<TestStep> steps = methodSteps.get("loginMethod");
//        List<TestStep> steps = methodSteps.get("loginMethod");
        if (steps == null) {
            throw new IllegalArgumentException("Method loginMethod not found in Excel");
        }

        for (TestStep step : steps) {
            switch (step.getAction().toLowerCase()) {
                case "click":
                    driver.findElement(LocatorReader.findLocatorByPartialName(step.getLocator())).click();
                    break;
                case "sendkeys":
                    driver.findElement(LocatorReader.findLocatorByPartialName(step.getLocator()))
                            .sendKeys((step.getValue().toString()));
                    break;
                case "wait":
                    Thread.sleep(Integer.parseInt(step.getValue()));
                    break;
            }
        }
    }

    private String replaceParameters(String value, Object... params) {
        if (value.startsWith("${") && value.endsWith("}")) {
            int paramIndex = Integer.parseInt(value.substring(2, value.length() - 1));
            return params[paramIndex].toString();
        }
        return value;
    }
}