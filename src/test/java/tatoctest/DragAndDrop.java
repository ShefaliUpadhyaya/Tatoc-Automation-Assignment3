package tatoctest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;

public class DragAndDrop {
	JavascriptExecutor js;
	
	public DragAndDrop(JavascriptExecutor js) {
		this.js = js;
	}
	
	private void clickProceed() {
		js.executeScript("document.getElementsByTagName('a')[0].click();");
	}
	
	public void dragAndDropNotPerformed() {
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Error");
	}
	
	public void dragAndDropPerformed() {
		js.executeScript("document.getElementById('dragbox').setAttribute('style', 'position: relative; left: 33px; top: -78px;')");
		clickProceed();
		String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
		assertEquals(title, "Popup Windows");
	}
}
