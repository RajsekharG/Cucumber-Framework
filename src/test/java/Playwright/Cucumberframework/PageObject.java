package Playwright.Cucumberframework;

import com.microsoft.playwright.Page;

public class PageObject {
	
	private Page page;
	
	public PageObject(Page page)
	{
		this.page=page;
	}
	
	private String Username="[name='username']";
	private String Password="[name='password']";
	private String LoginButton="button:has-text('Login')";
	
	public void enterlogindetails(String username, String password)
	{
		page.fill(Username, username);
		page.fill(Password, password);
	}
	
	public void clickloginbutton()
	{
		page.click(LoginButton);
	}
	

}
