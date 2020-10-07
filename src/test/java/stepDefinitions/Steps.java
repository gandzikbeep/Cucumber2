package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObcjects.LoginPage;

public class Steps extends BaseClass {


    @Given("User launch Chrome browser")
    public void user_launch_chrome_browser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        lp = new LoginPage(driver);

    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {
        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            Assert.assertTrue(false);
        } else {
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        lp.clickLogout();
        Thread.sleep(3000);
    }

    // Add new customers feature steps _________________________________________==================================

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {

    }

    @When("User click on customer Menu")
    public void user_click_on_customer_menu() {

    }
    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {

    }
    @When("click on Add new button")
    public void click_on_add_new_button() {

    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {

    }
    @When("User enter customer info")
    public void user_enter_customer_info() {

    }
    @When("click on Save button")
    public void click_on_save_button() {

    }
    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {

    }



    @Then("close browser")
    public void close_browser() {
        driver.quit();

    }


}
