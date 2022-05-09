import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CdpCommandTest {

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

		// set parameter values for setdevicemetricsovverride
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width", 600);
		deviceMetrics.put("height", 1000);
		deviceMetrics.put("deviceScaleFactor", 50);
		deviceMetrics.put("mobile", true);

		// bypass the send() method
		cdriver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
		Thread.sleep(3000);
		cdriver.findElement(By.linkText("Library")).click();

		Thread.sleep(5000);
		cdriver.quit();
	}

}
