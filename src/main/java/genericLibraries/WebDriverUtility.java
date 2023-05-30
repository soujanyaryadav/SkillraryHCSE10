package genericLibraries;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all reusable methods of webdriver
 * @author surya
 *
 */

public class WebDriverUtility {
	private WebDriver driver;
	
	/**
	 * This method is used to launch the browser
	 * @param browser
	 */
	
	public WebDriver launchBrowser(String browser) {
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser data");
			
			
		}
		return driver;
	}
			/**
			 * This method is used to maximize the browser
			 */
			public void maximizeBrowser() {
				driver.manage().window().maximize();
				
			}
			
			/**
			 * This method is used to navigate to the application via URL
			 */
			
			public void navigateToApp(String url) {
				driver.get(url);
			}
			
			/**
			 * This method waits until element or elements is found
			 */
 			
			public void waitUntilElementFound(long time) {
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			}
			
			public WebElement explicitWait(long time, WebElement element) {
				WebDriverWait wait = new WebDriverWait(driver, time);
			return wait.until(ExpectedConditions.visibilityOf(element));	
			}
			
			/**
			 * This method is used to wait until element is enabled
			 * @param element 
			 * @param element
			 * @param time 
			 * @param time
			 * @return
			 */
			public WebElement explicitWait(WebElement element, long time) {
					WebDriverWait wait = new WebDriverWait(driver,time);
					return wait.until(ExpectedConditions.elementToBeClickable(element));
			}
			/**
			 * This method is used to wait until element is enabled
			 * @param time
			 * @param title
			 * @return
			 */
			public boolean explicitwait(long time, String title) {
				WebDriverWait wait = new WebDriverWait(driver, time);
				return wait.until(ExpectedConditions.titleContains(title));
			}
			
			/**
			 * This method is used to wait until element is enabled
			 * @param element
			 */
			public void mouseHover(WebElement element) {
				Actions actions = new Actions(driver);
				actions.moveToElement(element).perform();
				
			}
			
			/** This method is used to double click on an element
			 
			 * @param element
			 */
			public void doubleClickOnElement(WebElement element) {
				Actions actions = new Actions(driver);
				actions.doubleClick(element).perform(); 
			}
			/**
			 * This method is used to right click on an Element
			 * @param element
			 */
			public void rightClick(WebElement element) {
				Actions actions = new Actions(driver);
				actions.contextClick(element).perform();
			}
			
			/**
			 * This method is used to drag and drop an element to target
			 * @param element
			 * @param target
			 */
			public void dragAndDropElement(WebElement element,WebElement target) {
				Actions actions = new Actions(driver);
				actions.dragAndDrop(element, target).perform();
			}
			
			/**
			 * This method is used to select an element from dropdown using index
			 * @param element
			 * @param index
			 */
			public void dropdown(WebElement element, int index) {
				Select s = new Select(element);
				s.selectByIndex(index);
			}
			
			/**
			 * This method is used to select an element from dropdown using value
			 * @param element
			 * @param value
			 */
			public void dropdown(WebElement element, String value) {
				Select s = new Select(element);
				s.selectByValue(value);
			}
			
			/**
			 * This method is used to select an element from dropdown using visible text
			 * @param text
			 * @param element
			 */
			public void dropdown(String text, WebElement element)  {
				Select s = new Select(element);
				s.selectByVisibleText(text);
			}
			/**
			 * This method is used to scroll till element
			 * @param element
			 */
			public void scrollTitleElement(WebElement element) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true)", element);
			}
			
			/**
			 * This method is used to switch to frame using index
			 * @param index
			 */
			public void switchToFrame(int index) {
				driver.switchTo().frame(index);
			}
			
			/**
			 * This method is used to switch to frame using id or name attribute value
			 * @param idOrName
			 */
			public void switchToFrame(String idOrName) {
				driver.switchTo().frame(idOrName);
			}
			
			/**
			 * this method is used to switch to frame using frame element
			 * @param frameElement
			 */
			public void switchToFrame(WebElement frameElement) {
				driver.switchTo().frame(frameElement);
				
				
			}
			
			/**
			 * This method is used to switch back from frame
			 */
			public void switchBackFromFrame() {
				driver.switchTo().defaultContent();
			}
			
			/**
			 * This method is used to take screenshot of window
			 */
			public void takesScreenshot(JavaUtility jUtil) {
				TakesScreenshot ts = (TakesScreenshot)driver;
				File src = ts .getScreenshotAs(OutputType.FILE);
				File dest = new File("./Screenshot/ss_"+jUtil.getCurrentTime()+".png");
				try {
					FileUtils.copyFile(src, dest);
				}catch (IOException e) {
					e.printStackTrace();					
				}
			}
			
			/**
			 * This method is used to handle alert
			 * @param status
			 */
			public void handleAlert(String status) {
				Alert al = driver.switchTo().alert();
				if(status.equalsIgnoreCase("ok"))
					al.accept();
				else
					al.dismiss();
			}
			
			/**
			 * This method is used to switch to child browser
			 */
			public void switchToChildBrowser() {
				Set<String> set = driver.getWindowHandles();
				for (String id : set) {
					driver.switchTo().window(id);
				}
				}
			
			/**
			 * This method is used to close current window
			 */
			
			public void closeCurrentWindow() {
				driver.close();
			}
			
			/**
			 * This method is used to close all windows
			 */
			public void quitAllWindows() {
				driver.quit();
			}
			
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
				
		

