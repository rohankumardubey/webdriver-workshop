package example.pages;

import static example.util.ElementInteraction.waitAndSendKeys;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends PageBase<AuthenticationPage> {

	@FindBy(id = "email")
	private WebElement emailTextfield;

	@FindBy(css = "input#passwd")
	private WebElement passwordTextField;

	@FindBy(css = "input#email_create")
	private WebElement emailCreatTextfield;

	@FindBy(css = "button#SubmitCreate")
	private WebElement createNewButton;

	@FindBy(css = "button#SubmitLogin")
	private WebElement loginButton;

	@FindBy(css = "div.alert ol>li")
	private WebElement errorMessage;

	@FindBy(xpath = "//input[@name = 'email_create']/../../div[contains(@class, 'form-group form-error')]")
	private WebElement emailErrorMessage;

	@FindBy(css = "h1.page-heading")
	private WebElement pageTitle;

	public AuthenticationPage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected void load() {
		driver.get("http://demo.rdekleijn.nl/index.php?controller=authentication");
	}

	@Override
	protected void isLoaded() throws Error {
		final String url = driver.getCurrentUrl();
		Assertions.assertThat(url.endsWith("authentication")).as("Not on the authentication entry page: " + url).isEqualTo(true);
	}

	public MyAccountPage loginWith(final String email, final String password) {
		waitAndSendKeys(emailTextfield, email);
		passwordTextField.sendKeys(password);
		loginButton.click();
		return new MyAccountPage(driver);
	}

	public boolean isEmailErrorShown() {
		return emailErrorMessage.isDisplayed();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public AccountCreationPage createNewAccount(final String email) {
		emailCreatTextfield.sendKeys(email);
		createNewButton.click();
		return new AccountCreationPage(driver);
	}
}
