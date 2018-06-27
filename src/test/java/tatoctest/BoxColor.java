package tatoctest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;

public class BoxColor {
	JavascriptExecutor js;
	String boxColor1, boxColor2;
	
	public BoxColor(JavascriptExecutor js) {
		this.js = js;
	}
	
	private void fetchBox1Color() {
		boxColor1 = js.executeScript("return document.getElementById('main').contentWindow.document.getElementById('answer').getAttribute('class')").toString();
	}
	
	private void fetchBox2Color() {
		boxColor2 = js.executeScript("return document.getElementById('main').contentWindow.document.getElementById('child').contentWindow.document.getElementById('answer').getAttribute('class')").toString();
	}
	
	private void clickProceed() {
		js.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[1].click();");
	}
	
	private void clickRepaint() {
		js.executeScript("document.getElementById('main').contentWindow.document.getElementsByTagName('a')[0].click();");
	}
	
	public void colorDoesNotMatch() {
		fetchBox1Color();
		fetchBox2Color();
		if(!(boxColor1.equals(boxColor2))) {
			clickProceed();
			String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
			assertEquals(title, "Error");
		}
	}
	
	public void colorMatches() {
		fetchBox1Color();
		do {
			fetchBox2Color();
			if(boxColor1.equals(boxColor2)) {
				clickProceed();
				String title = js.executeScript("return document.querySelector('body > div > div.page > h1').textContent;").toString();
				assertEquals(title, "Drag Around");
				break;
			}
			else clickRepaint();
		}
		while(!(boxColor1.equals(boxColor2)));
	}
}
