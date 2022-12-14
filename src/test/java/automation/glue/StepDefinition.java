package automation.glue;


import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.CheckoutPage;
import automation.pages.HomePage;
import automation.pages.SignInPage;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private CheckoutPage checkoutPage;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects() {
//        DriverSingleton.getInstance(configurationProperties.getBrowser());
        DriverSingleton.getInstance(Constants.CHROME);
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website() {
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("^I click on Sign In button")
    public void i_click_on_sign_in_button() {
        homePage.clickSignIn();
    }

    @And("^I specify my credentials and click Login")
    public void i_specify_my_credentials_and_click_login(){
        //signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
        signInPage.logIn(Constants.EMAIL,Constants.PASSWORD);
    }

    @Then("^I can log into the website")
    public void i_can_log_into_the_website(){
        assertEquals(configurationProperties.getUsername(),homePage.getUserName());
    }
}

