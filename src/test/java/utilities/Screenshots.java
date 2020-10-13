package utilities;

import org.apache.commons.io.FileUtils;
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

public class Screenshots {
    public WebDriver driver;

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

//    public void screenshotInCaseOfFailure(Scenario scenario) {
//        if (scenario.isFailed()) {
//            String screenshotName = (scenario.getName() + TimeTool.getCurrentLocalDateTime() + ".png").replace(" ", "_");
//            try {
//                Reporter.addScreenCaptureFromPath(ScreenshotTool.takeScreenshot(screenshotName));
//            } catch (Exception e) {
//                logger.error("Failure to capture a screenshot: {}", e);
//            }

    //    public  void takesScreenshot(WebDriver driver) throws IOException {
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        File screenshotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//        File destinationFile = new File("src/main/resources/test" + LocalTime.now().getNano() + ".png");
//        Files.copy(screenshotFile.toPath(),destinationFile.toPath());
//    }
//
//    public void getscreenshot(WebDriver driver) throws Exception {
//        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File dest = new File(("user.dir")+"screenshot" + LocalTime.now().getNano() + ".png");
//        FileUtils.copyFile(scr, dest);
//        Thread.sleep(3000);
//    }
    public static void takeScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
       File screenshotFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
       File destinationFile = new File ("src/main/resources/" + LocalTime.now().getNano() + ".png");
       Files.copy(screenshotFile.toPath(), destinationFile.toPath());

    }

//


}
