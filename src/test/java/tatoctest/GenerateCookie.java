package tatoctest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class GenerateCookie {
	WebDriver driver;
	JavascriptExecutor js;
	
	public GenerateCookie(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
	}
	
	private void clickGenerateToken() {
		js.executeScript("document.getElementsByTagName('a')[0].click();");
	}
	
	private void clickProceed() {
		js.executeScript("document.getElementsByTagName('a')[1].click();");
	}
	
	public void proceededWithoutGeneratingCookie() {
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void unableToGenerateCookie() {
		clickGenerateToken();
		Cookie cookie = new Cookie("Token", "123456789");
		js.executeScript("document.cookie='" + cookie + "'");
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void cookieGenerated() {
		clickGenerateToken();
		String token = js.executeScript("return document.getElementById('token').textContent;").toString();
		String tokenValue = token.substring(7);
		Cookie cookie = new Cookie("Token", tokenValue);
		js.executeScript("document.cookie='" + cookie + "'");
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "End");
	}
}
