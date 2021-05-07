package GlobalID.WordPress.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver,60);
		PageFactory.initElements(driver, this);
	}
	
	public boolean currentHTMLTitleIs(String expectedTitle) {
		boolean isExpectedTitle = false;
		if (driver.getTitle().equals(expectedTitle)) {
			isExpectedTitle = true;
		}
		
		return isExpectedTitle;
	}
	
}
