import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;
import org.openqa.selenium.devtools.v85.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v85.network.model.ErrorReason;

public class NetworkFailRequest {

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

		// create pattern for fetch.enable parameter GetBook API call
		Optional<List<RequestPattern>> patterns = Optional
				.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));

		// Enable network listen command
		devtools.send(Fetch.enable(patterns, Optional.empty()));

		// Pause an API method and set it to fail API call
		devtools.addListener(Fetch.requestPaused(), request -> {
			devtools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
		});

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		Thread.sleep(3000);

	}

}
