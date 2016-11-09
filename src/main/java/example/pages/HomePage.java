package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase<HomePage> {

	@FindBy(css = ".login")
	private WebElement loginLink;

	@FindBy(css = "#contact-link > a")
	private WebElement contactLink;

	@FindBy(css = "p.info-account")
	private WebElement message;

	public HomePage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected void load() {
		driver.get("http://www.demo.rdekleijn.nl");
	}

	@Override
	protected void isLoaded() throws Error {

	}

	public AuthenticationPage navigateToLoginPage() {
		loginLink.click();
		return new AuthenticationPage(driver);
	}

	public ContactPage navigateToContactPage() {
		contactLink.click();
		return new ContactPage(driver);
	}

	public String getMessage() {
		return message.getText();
	}
}
