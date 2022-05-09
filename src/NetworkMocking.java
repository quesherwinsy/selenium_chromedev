import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.fetch.Fetch;

public class NetworkMocking {

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

		// Enable network listen command
		devtools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		// Mock network request and update request
		// Pause an API method and replaces parameter, then continues new API
		devtools.addListener(Fetch.requestPaused(), request -> {
			if (request.getRequest().getUrl().contains("shetty")) {
				String newURL = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
				System.out.println(newURL);
				devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newURL),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty()));
			} else {
				devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(), Optional.empty()));
			}
		});

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.xpath("//button[@routerlink='/library']")).click();
		Thread.sleep(3000);
		System.out.println(cdriver.findElement(By.cssSelector("p")).getText());
	}

}
