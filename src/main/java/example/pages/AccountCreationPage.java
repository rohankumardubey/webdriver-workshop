package example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import example.type.Title;

public class AccountCreationPage extends LoadableComponent<AccountCreationPage> {
	private final WebDriver driver;

	@FindBy(css = "input#id_gender1")
	private WebElement maleRadio;

	@FindBy(css = "input#id_gender2")
	private WebElement femaleRadio;

	@FindBy(css = "input#customer_firstname")
	private WebElement firstnameTextfield;

	@FindBy(css = "input#customer_lastname")
	private WebElement lastnameTextfield;

	@FindBy(css = "input#email")
	private WebElement emailTextfield;

	@FindBy(css = "input#passwd")
	private WebElement passwordTextfield;

	@FindBy(css = "select#days")
	private WebElement daysSelect;
	@FindBy(css = "select#months")
	private WebElement monthsSelect;
	@FindBy(css = "select#years")
	private WebElement yearsSelect;

	@FindBy(css = "button#submitAccount")
	private WebElement registerButton;

	@Override
	protected void load() {
		driver.get("http://rdekleijn.nl/demo/login#account-creation");
	}

	@Override
	protected void isLoaded() throws Error {
		// verify if the correct page is open
		final String title = driver.getTitle();
		Assert.assertTrue(title.startsWith("Login"));
	}

	public AccountCreationPage(final WebDriver driver) {
		this.driver = driver;

		// This call sets the WebElement fields.
		PageFactory.initElements(driver, this);
	}

	public AccountCreationPage setTitle(final Title title) {
		if (title.equals(Title.MR)) {
			maleRadio.click();
		}
		if (title.equals(Title.MRS)) {
			femaleRadio.click();
		}
		return this;
	}

	public AccountCreationPage setFirstname(final String firstname) {
		firstnameTextfield.sendKeys(firstname);
		return this;
	}

	public AccountCreationPage setLastname(final String lastname) {
		lastnameTextfield.sendKeys(lastname);
		return this;
	}

	public AccountCreationPage setEmail(final String email) {
		emailTextfield.sendKeys(email);
		return this;
	}

	public AccountCreationPage setPassword(final String password) {
		passwordTextfield.sendKeys(password);
		return this;
	}

	public AccountCreationPage setDob(final String days, final String months, final String years) {
		Select dSelect = new Select(daysSelect);
		dSelect.selectByValue(days);
		Select mSelect = new Select(monthsSelect);
		mSelect.selectByValue(months);
		Select ySelect = new Select(yearsSelect);
		ySelect.selectByValue(years);
		return this;
	}

	public MyAccountPage clickOnRegister() {
		registerButton.click();
		return new MyAccountPage(driver);
	}

}
