package tatoctest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;

public class GridGate {
	JavascriptExecutor js;
	
	public GridGate(JavascriptExecutor js) {
		this.js = js;
	}
	
	public void clickRedBox() {
		js.executeScript("document.getElementsByClassName('redbox')[0].click();");
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void clickGreenBox() {
		js.executeScript("document.getElementsByClassName('greenbox')[0].click();");
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Frame Dungeon");
	}
}
