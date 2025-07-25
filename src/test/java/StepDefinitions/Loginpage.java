package StepDefinitions;

import java.io.IOException;
import org.junit.Assert;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Playwright.Cucumberframework.BaseClass;
import Playwright.Cucumberframework.PageObject;
import io.cucumber.java.en.*;

public class Loginpage extends BaseClass {
	
	
	PageObject pageobject;
	
	
	@Given("the user is on the login page.")
	public void the_user_is_on_the_login_page() throws IOException {
		
		browserinitialize();
	    pageobject=new PageObject(getPage());
	}

	@When("user enter's {string} and {string}.")
	public void user_enter_s_and(String username, String password) {
		pageobject.enterlogindetails(username, password);
		
	}

	@And("user clicks on the login button.")
	public void user_clicks_on_the_login_button() {
		
		pageobject.clickloginbutton();
	}

	@Then("the user navigates to the homepage.")
	public void the_user_navigates_to_the_homepage() {
		
HEAD
		String actualOutput = getPage().title();
//	    Locator userNameLocator = getPage().locator("//p[@class='oxd-userdropdown-name']");
//	    String observedoutput=userNameLocator.textContent();
//	    System.out.println(observedoutput);
	    Assert.assertEquals(actualOutput, "OrangeHRM","Title didnot matched");
	    String actualoutput="john desuza";
	    Locator userNameLocator = getPage().locator("//p[@class='oxd-userdropdown-name']");
	    String observedoutput=userNameLocator.textContent();
	    System.out.println(observedoutput);
	    Assert.assertEquals(actualoutput, observedoutput);

	}

}
