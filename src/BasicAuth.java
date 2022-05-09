import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.URI;
import java.util.function.Predicate;

public class BasicAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\home\\Desktop\\seleni\\chromedriver_win32\\chromedriver.exe");

		// web driver to access chrome tools
		ChromeDriver cdriver = new ChromeDriver();

		// java 8 new feature - predicates, lambda expressions, consumers
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
		// Useful for website testing which uses Basic authentication
		// Selenium checks for basic authentication and register it
		((HasAuthentication) cdriver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));

		cdriver.get("http://httpbin.org/basic-auth/foo/bar");

	}

}
