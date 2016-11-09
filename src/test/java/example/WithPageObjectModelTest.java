package example;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import example.pages.HomePage;

public class WithPageObjectModelTest extends AbstractTestBase {

	@Test(invocationCount = 15, threadPoolSize = 5)
	public void login() {
		final HomePage homepage = new HomePage(driver).get();
		homepage.navigateToLoginPage() //
				.loginWith("test@test.com", "1qazxsw2");

		Assertions.assertThat(homepage.getMessage()).as("success message").contains("Welcome to your account.");
	}

}
