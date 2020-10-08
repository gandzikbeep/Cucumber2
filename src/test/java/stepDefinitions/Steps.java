package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObcjects.AddCustomerPage;
import pageObcjects.LoginPage;
import pageObcjects.SearchCustomerPage;

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
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());


    }

    @When("User click on customer Menu")
    public void user_click_on_customer_menu() throws InterruptedException {
        addCust.clickOnCustomersMenu();
        Thread.sleep(3000);

    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        addCust.clickOnAddnew();

    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() throws InterruptedException {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
        Thread.sleep(3000);
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {


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
        Thread.sleep(3000);
        driver.quit();

    }
// Searching Email using EmailId ============================================================

    @When("Enter customer Email")
    public void enter_customer_email() {
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
        boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
        Assert.assertEquals(true, status);
    }

    // Search by First Name and Last Name ===============================================================
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {

    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {

    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {

    }


}
