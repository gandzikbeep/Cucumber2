package utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;

public class WaitHelper {

    public WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void WaitForElement(WebElement element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void newScr(Scenario scenario) throws IOException {

        File screenshotFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/main/resources/" +
                scenario.getName() + LocalTime.now().getNano() + ".png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());

    }



}
