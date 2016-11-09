package assignment.refactored;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import example.ScreenshotListener;

@Listeners({ ScreenshotListener.class })
public class AbstractTestBase {

	public static ChromeDriver driver;
	// public static RemoteWebDriver driver;

	@BeforeClass
	public void setup() throws Exception {
		// System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/mac/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://www.demo.rdekleijn.nl");
		//
		// final DesiredCapabilities dr = DesiredCapabilities.chrome();
		// driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), dr);
		// driver.get("http://www.demo.rdekleijn.nl");

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
