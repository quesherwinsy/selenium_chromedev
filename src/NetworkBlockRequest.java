import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;

import com.google.common.collect.ImmutableList;

public class NetworkBlockRequest {

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

		// Enable network traffic command
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Block a URL list, to make web page run faster
		devtools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*css")));

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.linkText("Browse Products")).click();
		Thread.sleep(2000);
		cdriver.findElement(By.linkText("Selenium")).click();
		Thread.sleep(2000);
		cdriver.findElement(By.cssSelector(".add-to-cart")).click();
		Thread.sleep(2000);
		System.out.println(cdriver.findElement(By.cssSelector("p")).getText());

	}
}
