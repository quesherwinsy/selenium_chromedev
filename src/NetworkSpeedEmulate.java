import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;

public class NetworkSpeedEmulate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\home\\Desktop\\seleni\\chromedriver_win32\\chromedriver.exe");

		// web driver to access chrome tools
		ChromeDriver cdriver = new ChromeDriver();
		// initialize dev tools object
		DevTools devtools = cdriver.getDevTools();
		// create session for dev tools
		devtools.createSession();

		// Enable network traffic command
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Emulate network type of Ethernet speed
		devtools.send(
				Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET)));

		// Event listeners for failed network
		devtools.addListener(Network.loadingFailed(), loadingFailed -> {
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
		});

		long startTim = System.currentTimeMillis();
		// cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		// cdriver.findElement(By.xpath("//button[@routerlink='/library']")).click();

		cdriver.get("https://www.google.com/");
		cdriver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
		cdriver.findElements(By.cssSelector(".LC20lb")).get(0).click();

		long endTim = System.currentTimeMillis();
		System.out.println("time is: " + (endTim - startTim));
		// cdriver.close();
	}

}
