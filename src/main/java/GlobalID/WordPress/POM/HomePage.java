package GlobalID.WordPress.POM;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='searchsubmit']/preceding-sibling::input")
	private WebElement searchInput;
	
	
	private List<WebElement> postTimes;
	private List<WebElement> categories;
	
	public void searchBlogByText(String blogText) {
		
		searchInput.sendKeys(blogText + Keys.ENTER);
	}
	
	public void clickSearchPostResultByTitle(String blogTitle) {
		
		List<WebElement> searchResults = driver.findElements(By.xpath("//section//h4/a"));
		for (WebElement result : searchResults) {
			if(result.getAttribute("title").equals(blogTitle)){
				result.click();
			}
		}
		
	}


	public Boolean postTimeIsDescending() throws ParseException {
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy HH:mm");
		ArrayList<Date> blogDates = new ArrayList<Date>();
		Boolean isDescending = true;
		
		postTimes = driver.findElements(By.xpath("//section//div[@class='post-date']"));

		for (WebElement postTime : postTimes) {

			blogDates.add(formatter.parse(postTime.getText()));
			
		}
		
		for (int i = 0; i < blogDates.size() - 1; i++) {

			if(blogDates.get(i).before(blogDates.get(i+1))) {
				isDescending = false;
				break;
			}
		}
		
		return isDescending;
		
	}
	
	public Boolean categoriesAreClickable() {
	
		categories = driver.findElements(By.xpath("//li[contains(@class,'cat-item')]/a"));
		int categoriesCount = categories.size();
		
		for (int i = 1; i < categoriesCount + 1; i++) {
			
			driver.findElement(By.xpath("(//li[contains(@class,'cat-item')]/a)["+i+"]")).click();
			wait.until(ExpectedConditions.urlContains("cat="));
			driver.navigate().back();
			
		}
		
		return true;
	}
	
	public Boolean categoriesAreCorrect() {
		
		categories = driver.findElements(By.xpath("//li[contains(@class,'cat-item')]/a"));
		int categoriesCount = categories.size();
		
		for (int i = 1; i < categoriesCount + 1; i++) {
			
			WebElement category = driver.findElement(By.xpath("(//li[contains(@class,'cat-item')]/a)["+i+"]"));
			String categoryText = category.getText();
			
			category.click();
			
			List<WebElement> categoryByPost = driver.findElements(By.xpath("//div/a[@rel='category']"));
			for (int j = 0; j < categoryByPost.size(); j++) {

				if(!categoryByPost.get(j).getText().equals(categoryText))
				{
					return false;
				}
			}
			driver.navigate().back();
			
		}
		
		return true;
	}

	
}
