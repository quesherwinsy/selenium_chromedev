import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.Response;

public class NetworkLogActivity {

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

		// log file

		// Enable network traffic command
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// event listener for network requestWillBeSent() method
		devtools.addListener(Network.requestWillBeSent(), request -> {
			Request req = request.getRequest();
			System.out.println(req.getUrl());
			// req.getHeaders();
		});

		// event listener for network response received() method
		devtools.addListener(Network.responseReceived(), response -> {
			Response res = response.getResponse();
			// System.out.println(res.getUrl());
			if (res.getStatus().toString().startsWith("4")) {
				System.out.println(res.getUrl() + " is failing with code " + res.getStatus());
			}
		});

		cdriver.get("https://www.rahulshettyacademy.com/angularAppdemo/");
		cdriver.findElement(By.xpath("//button[@routerlink='/library']")).click();
	}

}
