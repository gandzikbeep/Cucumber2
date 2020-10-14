package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObcjects.AddCustomerPage;
import pageObcjects.LoginPage;
import pageObcjects.SearchCustomerPage;
import utilities.Screenshots;
import utilities.WaitHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.Properties;

public class Steps extends BaseClass {
    WebDriver driver;
//    Screenshots screenshots;


    @Before
    public void setup() throws IOException {
        //logger configuration
        logger = Logger.getLogger("nopCommerce"); // Added logger
        PropertyConfigurator.configure("Log4j.properties");   //Added logger

        //Reading properties file
        configProp = new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);

        String br = configProp.getProperty("browser");

        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        } else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        } else if (br.equals("ie")) {
            System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
            driver = new InternetExplorerDriver();
        }
        logger.info("************ Lauching browser ************");
    }


    @Given("User launch Chrome browser")
    public void user_launch_chrome_browser() {
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        logger.info(" ************ Opening URL ************");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) throws IOException {
        logger.info("************ Providing login details ************");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
        logger.info("************ started login ************");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {

        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            logger.info(" ************ Login failed ************");

            Assert.fail();
        } else {
            logger.info(" ************ Login passed ************");
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        logger.info(" ************ click on logout btn ************");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    // Add new customers feature steps _________________________________________==================================

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        logger.info(" ************ After Login, user see Dashboard ************");
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User click on customer Menu")
    public void user_click_on_customer_menu() throws InterruptedException {
        logger.info("************ Clicking on Customer menu ************");
        addCust.clickOnCustomersMenu();
        Thread.sleep(3000);
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {
        logger.info("************ Clicking on Customer menu  - ITEM ************");
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        logger.info("************ Clicking on ADD NEW button - Adding a new customer ************");
        addCust.clickOnAddnew();

    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() throws InterruptedException {
        logger.info("************ User is on Add new Customer page ************");
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
        Thread.sleep(3000);
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        logger.info("************ Adding a new Customer ************");
        logger.info("Providing customer details");

        String email = randomestring() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        addCust.setFirstName("Anna");
        addCust.setLastName("Kowalska");
        addCust.setGender("F");
        addCust.setDob("7/05/1985");
        addCust.setCompanyName("CompanyName");

        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        Thread.sleep(3000);

        addCust.setAdminContent("This is testing .......");
    }

    @When("click on Save button")
    public void click_on_Save_button() {
        logger.info("************ Saving customer data ************");
        addCust.clickOnSave();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() throws InterruptedException {
        logger.info(" ************ Closing browser ************");
        Thread.sleep(3000);
        driver.close();
    }
// Searching Email using EmailId ============================================================

    @When("Enter customer Email")
    public void enter_customer_email() {
        logger.info("************ Searching customer by using email id  ************");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        logger.info("************ User should see the search expectations - EMAIL   ************");
        boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertTrue(status);
    }

    // Search by First Name and Last Name ===============================================================
    @When("Enter customer FirstName")
    public void enter_customer_first_name() throws IOException {
        logger.info("************ User enters first name  ************");
        searchCust = new SearchCustomerPage(driver);
        searchCust.setFirstName("Victoria");
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        logger.info("************ Searching customer by using Name  ************");

        searchCust.setLastName("Terces");
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        logger.info("************ User should see the search expectations - NAME (First and Last)   ************");
        boolean status = searchCust.searchCustomerByName("Victoria Terces");
        Assert.assertTrue(status);
    }


    @After
    public void endTest(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            logger.info("Scenario is failed");
            Screenshots screenshots = new Screenshots(driver);
            screenshots.newScr(scenario);
            System.out.println("test");
            System.out.println("test");
        }
        logger.info("End test");
        driver.quit();



        //screenshots.takeScrOnFail();
      //  driver.quit();
    }

//public void scrAfterStep(Scenario scenario) throws Exception {
//    if(scenario.isFailed()){
//        try {
//        Screenshots screenshots = new Screenshots(driver);
//        screenshots.getscreenshot(driver);
//        } catch (FileNotFoundException ignored) {}
//
//    }
//}


}



