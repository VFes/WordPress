package GlobalID.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	private WebDriver driver;
	
	public WebDriver init(Browsers browser) {
		
		switch (browser) {
		case Chrome:
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			manageDriver(driver);
			break;

		default:
			break;
		}
		
		return driver;
		
	}

	private void manageDriver(WebDriver driver2) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
	}
	
}
