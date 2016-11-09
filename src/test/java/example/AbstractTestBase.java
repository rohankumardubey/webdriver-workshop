package example;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

/**
 * This abstract class takes care of the browser startup and teardown
 * 
 * Abstract classes cannot be instantiated, but they can be subclassed.
 */
@Listeners({ ScreenshotListener.class })
public abstract class AbstractTestBase {
	protected static WebDriver driver;

	@BeforeClass
	public void setup() throws Exception {
		// System.setProperty("webdriver.gecko.driver", "/Users/roydekleijn/Downloads/geckodriver-0.8.0-OSX");
		// driver = new MarionetteDriver();
		// driver.get("http://www.demo.technisch-testen.nl");

		final DesiredCapabilities dr = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), dr);
		driver.get("http://www.demo.technisch-testen.nl");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
