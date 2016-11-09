package assignment.refactored;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import example.pages.AuthenticationPage;
import example.pages.HomePage;
import example.pages.MyAccountPage;

@Test
public class LoginTest extends AbstractTestBase {

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter valid credentials <br>
	 * 3. verify that you are logged in
	 */
	public void validCredentialsGiven_shouldLogin() {
		MyAccountPage accountPage = new HomePage(driver).get().navigateToLoginPage().loginWith("test@test.com", "qwerty");

		final String title = accountPage.getWelcomeMessage();
		Assertions.assertThat(title).isEqualTo("Welcome to your account. Here you can manage all of your personal information and orders.");
	}

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter invalid credentials <br>
	 * 3. verify error message
	 */
	public void invalidCredentialsGiven_shouldShowErrorMessage() {
		final AuthenticationPage loginPage = new HomePage(driver).get().navigateToLoginPage();
		loginPage.loginWith("test@test.com", "ytrewq");

		Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Authentication failed.");
	}

	/**
	 * Create a testcase which will do the following: <br>
	 * 
	 * 1. navigate to login page <br>
	 * 2. enter invalid emailaddress format <br>
	 * 3. verify error message
	 */
	public void invalidEmailAddressGiven_shouldShowErrorMessage() {
		final AuthenticationPage loginPage = new HomePage(driver).get().navigateToLoginPage();
		loginPage.loginWith("test@test", "qwerty");

		Assertions.assertThat(loginPage.getErrorMessage()).isEqualTo("Invalid email address.");
	}

}
