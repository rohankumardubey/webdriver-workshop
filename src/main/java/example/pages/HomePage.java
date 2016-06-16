package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase<HomePage> {

	@FindBy(css = ".login")
	private WebElement loginLink;

	@FindBy(css = "p.info-account")
	private WebElement message;

	public HomePage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected void load() {
		driver.get("http://demo.technisch-testen.nl");
	}

	@Override
	protected void isLoaded() throws Error {

	}

	public LoginPage navigateToLoginPage() {
		loginLink.click();
		return new LoginPage(driver);
	}

	public String getMessage() {
		return message.getText();
	}
}
