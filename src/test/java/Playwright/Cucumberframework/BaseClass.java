package Playwright.Cucumberframework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseClass {

	Properties prop;
	protected static ThreadLocal<Playwright> tlPlaywright=new ThreadLocal<>();
	protected static ThreadLocal<Browser> tlBrowser=new ThreadLocal<>();
	protected static ThreadLocal<Page> tlPage=new ThreadLocal<>();
	protected static ThreadLocal<BrowserContext> tlContext=new ThreadLocal<>();

	public static Playwright getPlaywright()
	{
		return tlPlaywright.get();
	}

	public static Browser getBrowser()
	{
		return tlBrowser.get();
	}

	public static Page getPage()
	{
		return tlPage.get();
	}

	public static BrowserContext getContext()
	{
		return tlContext.get();
	}

	public Page browserinitialize() throws IOException
	{
		loadProperties();
		String browsername=prop.getProperty("browser");
		String basename=prop.getProperty("url");
		boolean isheadless=Boolean.parseBoolean("headless");
		BrowserType.LaunchOptions options=new BrowserType.LaunchOptions().setHeadless(isheadless);
		tlPlaywright.set(Playwright.create());

		switch(browsername)
		{
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(options));
			break;

		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(options));
			break;

		case "webkit":
			tlBrowser.set(getPlaywright().webkit().launch(options));
			break;

		case "chrome":
			options.setChannel("chrome");
			tlBrowser.set(getPlaywright().chromium().launch(options));
			break;

		case "msedge":
			options.setChannel("msedge");
			tlBrowser.set(getPlaywright().chromium().launch(options));
			break;

		default:
			System.out.println("Please enter the valid browsername:"+browsername);
		}

		tlContext.set(getBrowser().newContext());
		tlPage.set(getContext().newPage());
		getPage().navigate(basename);
		
		return getPage();
	}

	public void loadProperties() throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resource/Resources/TestData.Resources");
		prop=new Properties();
		prop.load(fis);
	}
	
	
	public void teardown()
	{
		if(getPlaywright()!=null)
		{
			getPlaywright().close();
		}
	}

}
