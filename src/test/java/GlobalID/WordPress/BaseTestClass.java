package GlobalID.WordPress;

import GlobalID.tools.BrowserFactory;
import GlobalID.tools.Browsers;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class BaseTestClass {
	
  protected WebDriver driver;
  protected BrowserFactory factory = new BrowserFactory();

}
