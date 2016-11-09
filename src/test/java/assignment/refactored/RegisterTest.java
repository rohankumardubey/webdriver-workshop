package assignment.refactored;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import example.pages.AuthenticationPage;

@Test
public class RegisterTest extends AbstractTestBase {

	public void wrongEmailGiven_shouldShowErrorMessage() {
		final AuthenticationPage authenticationPage = new AuthenticationPage(driver).get();
		authenticationPage.createNewAccount("some-emailest.nl");

		Assertions.assertThat(authenticationPage.isEmailErrorShown()).isEqualTo(true);
	}

	public void validDataGiven_shouldRegister() {

	}
}
