package tatoctest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;

public class BasicCourse {
	
	JavascriptExecutor js;
	
	public BasicCourse(JavascriptExecutor js) {
		this.js = js;
	}
	
	public void clickButton() {
		js.executeScript("document.getElementsByTagName('a')[0].click();");
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Grid Gate");
	}
}
