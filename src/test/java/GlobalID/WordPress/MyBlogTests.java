package GlobalID.WordPress;

import org.testng.annotations.Test;

import GlobalID.WordPress.POM.HomePage;
import GlobalID.WordPress.POM.PostPage;
import GlobalID.tools.BrowserFactory;
import GlobalID.tools.Browsers;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.assertTrue;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class MyBlogTests {
	protected WebDriver driver;
	protected BrowserFactory factory = new BrowserFactory();
	String mainURL = "http://localhost:8000/";

	@Test (priority=1)	
	public void TestBlogPageOpens() {
		//Setup
		String title = "Pueblos Mágicos – Mexico's magical towns";
		HomePage home = new HomePage(driver);
		
		//Verify
		assertTrue(home.currentHTMLTitleIs(title));
	}
	
	@Test (priority=2)	
	public void TestLatestBlogPostIsCorrect() throws ParseException {
		//Setup
		HomePage home = new HomePage(driver);
		
		//Verify
		assertTrue(home.postTimeIsDescending());
	}
	
	@Test (priority=3)	
	public void TestCategoriesAreClickable(){
		//Setup
		HomePage home = new HomePage(driver);
		
		//Verify
		assertTrue(home.categoriesAreClickable());
	}
	
	@Test (priority=4)	
	public void TestPostByCategory() {
		//Setup
		HomePage home = new HomePage(driver);
		
		//Verify
		assertTrue(home.categoriesAreCorrect());
	}
	
	@Test (priority=5)	
	public void TestOpenBlogPost() {
		//Setup
		HomePage home = new HomePage(driver);
		PostPage post = new PostPage(driver);
		String blogPostTitle = "Yuriria, Guanajuato";
		
		//Test
		home.searchBlogByText(blogPostTitle);
		home.clickSearchPostResultByTitle(blogPostTitle);
		
		//Verify
		assertTrue(post.titleNameIs(blogPostTitle));
		
	}
	
	@Test (priority=6)	
	public void TestCommentBlogPost() {
		//Setup
		HomePage home = new HomePage(driver);
		PostPage post = new PostPage(driver);
		String blogPostTitle = "Tequila, Jalisco";
		String commentText = "This is a test comment "+ new java.util.Date().toString();
		String name = "Host99";
		String email = "host99@email.com";
		
		//Test
		home.searchBlogByText(blogPostTitle);
		home.clickSearchPostResultByTitle(blogPostTitle);
		post.postComment(commentText, name, email);
		
		//Verify
		assertTrue(post.commentIsPosted(commentText));
		
	}

	@BeforeMethod
	public void beforeTest() {
		driver = factory.init(Browsers.Chrome);
		driver.get(mainURL);
	}

	@AfterMethod
	public void afterTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
