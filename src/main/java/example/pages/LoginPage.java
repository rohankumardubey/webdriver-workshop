package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase<LoginPage> {

	@FindBy(css = "input#email")
	private WebElement emailTextfield;

	@FindBy(css = "input#passwd")
	private WebElement passwordTextField;

	@FindBy(css = "button#SubmitLogin")
	private WebElement loginButton;

	public LoginPage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
	}

	public void loginWith(final String email, final String password) {
		emailTextfield.sendKeys(email);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}

}
