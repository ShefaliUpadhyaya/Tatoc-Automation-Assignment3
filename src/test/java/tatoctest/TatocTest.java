package tatoctest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TatocTest {
	WebDriver driver;
	JavascriptExecutor js;
	BasicCourse basicCourse;
	GridGate gridGate;
	BoxColor boxColor;
	DragAndDrop dragAndDrop;
	PopupWindow popupWindow;
	GenerateCookie generateCookie;

	@BeforeClass
	public void launchBrowser() {
		driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;	
		driver.get("http://10.0.1.86/tatoc");
		basicCourse = new BasicCourse(js);
		gridGate = new GridGate(js);
		boxColor = new BoxColor(js);
		dragAndDrop = new DragAndDrop(js);
		popupWindow = new PopupWindow(driver, js);
		generateCookie = new GenerateCookie(driver, js);
    }
	
	@Test(priority = 1)
	public void basic_Course_Button_Should_Click() {
		basicCourse.clickButton();
	}
	
	@Test(priority = 2)
	public void red_Box_Click_Should_Display_Error_Page() {
		gridGate.clickRedBox();
	}
	
	@Test(priority = 3)
	public void green_Box_Click_Should_Go_To_Next_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/grid/gate'");
		gridGate.clickGreenBox();
	}
	
	@Test(priority = 4)
	public void box_Colors_Do_Not_Match_Should_Display_Error_Page() {
		boxColor.colorDoesNotMatch();
	}
	
	@Test(priority = 5)
	public void box_Colors_Match_Should_Go_To_Next_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/frame/dungeon'");
		boxColor.colorMatches();
	}
	
	@Test(priority = 6)
	public void drag_Me_Box_Not_In_DropBox_Goes_To_Error_Page() {
		dragAndDrop.dragAndDropNotPerformed();
	}
	
	@Test(priority = 7)
	public void drag_Me_Box_In_DropBox_Goes_To_Next_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/drag'");
		dragAndDrop.dragAndDropPerformed();
	}
	
	@Test(priority = 8)
	public void clicked_Proceed_Without_Launching_Popup_Window_Goes_To_Error_Page() {
		popupWindow.proceededWithoutLaunchingPopup();
	}
	
	@Test(priority = 9)
	public void launched_Popup_Window_But_Form_Submitted_Blank_Goes_To_Error_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/windows'");
		popupWindow.formSubmittedBlank();
	}
	
	@Test(priority = 10)
	public void launched_Popup_Window_And_Form_Submitted_Properly_Goes_To_Next_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/windows'");
		popupWindow.formSubmittedProperly();
	}
	
	@Test(priority = 11)
	public void clicked_Proceed_Without_Generating_Token_Goes_To_Error_Page() {
		generateCookie.proceededWithoutGeneratingCookie();
	}
	
	@Test(priority = 12)
	public void cookie_Not_Generated_Properly_Goes_To_Error_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/cookie'");
		generateCookie.unableToGenerateCookie();
	}
	
	@Test(priority = 13)
	public void cookie_Generated_Properly_Goes_To_Course_Completion_Page() {
		js.executeScript("window.location = 'http://10.0.1.86/tatoc/basic/cookie'");
		generateCookie.cookieGenerated();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
