package GlobalID.WordPress.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends BasePage{
	
	public PostPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath =  "//section//h1[@class='pagetitle-title heading']")
	private WebElement title;
	
	@FindBy(id = "comment")
	private WebElement commentTextBox;
	
	@FindBy(id = "author")
	private WebElement NameTextBox;
	
	@FindBy(id = "email")
	private WebElement emailTextBox;
	
	@FindBy(id = "submit")
	private WebElement postCommentButton;
	
	@FindBy(xpath = "//div[@class='comment-content']/p")
	private WebElement lastComment;
	
	public Boolean titleNameIs(String expectedTitleName) {
		if(title.getText().equals(expectedTitleName)) {
			return true;
		}
		else {return false;}
	}
	
	public void postComment(String commentText, String name, String email) {
		
		commentTextBox.sendKeys(commentText);
		NameTextBox.sendKeys(name);
		emailTextBox.sendKeys(email);
		
		postCommentButton.click();
		
	}
	
	public Boolean commentIsPosted(String commentText) {
		if(lastComment.getText().equals(commentText)) {
			return true;
		}
		else{return false;}
	}

}
