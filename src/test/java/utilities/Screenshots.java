package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Screenshots {
    public WebDriver driver;
//    private static Object driver;


    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    public void newScr(Scenario scenario) throws IOException {

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src/main/resources/" +
                scenario.getName() + " " +  getCurrentDateAndTime() + ".png");
        Files.copy(screenshotFile.toPath(), destinationFile.toPath());
    }

    private String getCurrentDateAndTime() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }


}


//public static void mateuszaScr(){
//        File screenSource = ((TakesScreenshot) mobileDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
//        FileHandler.copy(screenSource, new File(screenshotsDir + "_" + scenario.getName() + timeToPrint + ".png"));
//
//        byte[] screenSource2 = ((TakesScreenshot) mobileDriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
//        scenario.embed(screenSource2, "image/png", timeToPrint);
//
//
//}


//



