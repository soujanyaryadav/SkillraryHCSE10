package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

/**
 * This class contains element and respective business libraries of SkillRary Demo App page
 * @author surya
 *
 */

public class SkillraryDemoAppPage {
	
	//Declaration
	
	@FindBy(xpath="//div[@class='navbar-header']")
	private WebElement pageHeader;
	
	@FindBy(id="course")
	private WebElement courseTab;
	
	@FindBy(xpath="//span/a[text()='Selenium Training']")
	private WebElement seleniumTrainingLink;
	
	@FindBy(name="addresstype")
	private WebElement categoryDD;
	
	@FindBy(xpath="//a[text()='Contact Us']")
	private WebElement contactUsLink;
	
	//Initialization
	
	public SkillraryDemoAppPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	/**
	 * This method returns page header text
	 * @return
	 */
	public String getPageHeader() {
		return pageHeader.getText();	
		}
	
	/**
	 * this method is used to mouse hover to course tab
	 * @param web
	 */
	
	public void mouseHoverToCourse(WebDriverUtility web) {
		web.mouseHover(courseTab);
	}
	
	/**
	 * This method is used to click on selenium Training
	 */
	public void clickSeleniumTraining() {
		seleniumTrainingLink.click();
	}
	
	/**
	 * This method is used to select category
	 * @param web
	 * @param index
	 */
	public void selectCategory(WebDriverUtility web, int index) {
		web.dropdown(categoryDD, index);
	}
	
	/**
	 * This method returns contact us link
	 * @return
	 */
	public WebElement getContactUsLink() {
		return contactUsLink;
	}
	
	/**
	 * This method is used to click on Contact us link
	 */
	public void clickContactUs() {
		contactUsLink.click();
	}
	
	
	
	

}
