package tatoctest;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PopupWindow {
	WebDriver driver;
	JavascriptExecutor js;
	String parentWindowHandler; 
	String subWindowHandler = null;
	
	public PopupWindow(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
		parentWindowHandler = driver.getWindowHandle(); 
	}
	
	private void launchPopupWindow() {
		js.executeScript("document.getElementsByTagName('a')[0].click();");
	}
	
	private void clickProceed() {
		js.executeScript("document.getElementsByTagName('a')[1].click();");
	}
	
	public void proceededWithoutLaunchingPopup() {
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void subWindowsCapturingAndInputting(String formFieldString) {
		Set<String> handles = driver.getWindowHandles(); 
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next().toString();
		    if(!subWindowHandler.contains(parentWindowHandler)) {
		    	driver.switchTo().window(subWindowHandler);
		    	js.executeScript("document.getElementById('name').value = '" + formFieldString + "';");
		    	js.executeScript("document.getElementById('submit').click();");
		    	driver.switchTo().window(parentWindowHandler);
		    }
		}
		clickProceed();
	}
	
	public void formSubmittedBlank() {
		launchPopupWindow();
		subWindowsCapturingAndInputting("");
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void formSubmittedProperly() {
		launchPopupWindow();
		subWindowsCapturingAndInputting("Hello");
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Cookie Handling");
	}
}
