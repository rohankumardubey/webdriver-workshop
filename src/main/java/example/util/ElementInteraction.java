package example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementInteraction {

	private static WebDriverWait wait;

	public static void init(final WebDriver driver) {
		wait = new WebDriverWait(driver, 25, 500);
	}

	public static void waitAndClick(final WebElement el) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
		el.click();
	}

	public static void waitAndSendKeys(final WebElement el, final String text) {
		wait.until(ExpectedConditions.elementToBeClickable(el));
		el.sendKeys(text);
	}

}
