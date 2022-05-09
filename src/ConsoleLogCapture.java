import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogCapture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\home\\Desktop\\seleni\\chromedriver_win32\\chromedriver.exe");

		// web driver to access chrome tools
		ChromeDriver cdriver = new ChromeDriver();

		// implicit wait 5 seconds
		cdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// maximize the window screen
		cdriver.manage().window().maximize();

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.linkText("Browse Products")).click();

		cdriver.findElement(By.linkText("Selenium")).click();
		cdriver.findElement(By.xpath("//button[contains(@class,'add-to-cart')]")).click();
		cdriver.findElement(By.xpath("//a[@routerlink='/products']")).click();

		cdriver.findElement(By.linkText("Appium")).click();
		cdriver.findElement(By.xpath("//button[contains(@class,'add-to-cart')]")).click();
		cdriver.findElement(By.xpath("//a[@routerlink='/products']")).click();

		cdriver.findElement(By.xpath("//a[@routerlink='/cart']")).click();
		cdriver.findElement(By.xpath("//input[@type='email']")).clear();
		// This will cause a bug
		cdriver.findElement(By.xpath("//input[@type='email']")).sendKeys("2");

		// Selenium get log entry object from web browser
		LogEntries logEnt = cdriver.manage().logs().get(LogType.BROWSER);
		List<LogEntry> elogs = logEnt.getAll();

		for (LogEntry e : elogs) {
			System.out.println(e.getMessage());
			// Try extract this to log4j file
		}

	}

}
