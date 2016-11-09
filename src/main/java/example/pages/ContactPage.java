package example.pages;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ContactPage extends PageBase<ContactPage> {

	@FindBy(css = ".contact-form-box")
	private List<WebElement> contactForm;

	@FindBy(css = "h1.page-heading")
	private WebElement pageTitle;

	@FindBy(css = "select#id_contact")
	private WebElement subjectSelect;

	@FindBy(css = "input#email")
	private WebElement emailField;

	@FindBy(css = "input#id_order")
	private WebElement referenceField;

	@FindBy(css = "textarea#message")
	private WebElement messageField;

	@FindBy(css = "button#submitMessage")
	private WebElement submitButton;

	@FindBy(css = "p.alert-success")
	private WebElement successMessage;

	@FindBy(css = "div.alert ol>li")
	private WebElement errorMessage;

	public ContactPage(final WebDriver driver) {
		super(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		final String url = driver.getCurrentUrl();
		Assertions.assertThat(!contactForm.isEmpty()).as("Not on the contact entry page: " + url).isEqualTo(true);
	}

	@Override
	protected void load() {
		driver.get("http://demo.rdekleijn.nl/index.php?controller=contact");
	}

	public String getPageTitle() {
		return pageTitle.getText();
	}

	public ContactPage completeForm(final ContactSubject subject, final String email, final String orderReference, final String message) {
		new Select(subjectSelect).selectByVisibleText(subject.toString());
		emailField.sendKeys(email);
		referenceField.sendKeys(orderReference);
		messageField.sendKeys(message);
		submitButton.click();
		return this;
	}

	public String getSuccessMessage() {
		return successMessage.getText();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

}
