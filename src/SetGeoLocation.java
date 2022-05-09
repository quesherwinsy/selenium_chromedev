import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class SetGeoLocation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\home\\Desktop\\seleni\\chromedriver_win32\\chromedriver.exe");

		// web driver to access chrome tools
		ChromeDriver cdriver = new ChromeDriver();
		// initialize dev tools object
		DevTools devtools = cdriver.getDevTools();
		// create session for dev tools
		devtools.createSession();

		// set parameter values for setGeolocationOverride() to set location
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("latitude", 40);
		deviceMetrics.put("longitude", 3);
		deviceMetrics.put("accuracy", 1);

		// bypass the send() method
		cdriver.executeCdpCommand("Emulation.setGeolocationOverride", deviceMetrics);

		cdriver.get("https://www.google.com/");
		cdriver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
		cdriver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		Thread.sleep(2000);
		String title = cdriver.findElement(By.cssSelector(".our-story-card-title")).getText();
		System.out.println(title);
		Thread.sleep(5000);
		cdriver.quit();
	}

}
