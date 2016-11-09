package example;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WebDriverWaitExample extends AbstractTestBase {

	@Test
	public void waitForElementToBeClickable() {
		new WebDriverWait(driver, 20, 100).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.login")));
	}

	@Test
	public void waitForElementNotToBeVisable() {
		new WebDriverWait(driver, 20, 100).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("loader")));
	}

}
