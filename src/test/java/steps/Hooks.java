package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BasePage;

public class Hooks extends BasePage {
    public Hooks() {
        super(driver);
    }

    @After
    public static void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            //scenario.write("Escenario fallando, referirse a la imagen adjunta.");
            final byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            scenario.log("Escenario fallando, referirse a la imagen adjunta.");
        }
    }

}