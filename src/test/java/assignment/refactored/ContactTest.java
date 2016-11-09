package assignment.refactored;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import example.pages.ContactPage;
import example.pages.ContactSubject;
import example.pages.HomePage;

@Test
public class ContactTest extends AbstractTestBase {

	public void navigateToContactPage_contactPageShown() {
		final ContactPage contactPage = new HomePage(driver).get().navigateToContactPage();
		Assertions.assertThat(contactPage.getPageTitle()).isEqualTo("CUSTOMER SERVICE - CONTACT US");
	}

	public void completeContactForm_confirmationShown() {
		final ContactPage contactPage = new ContactPage(driver)
				.get()
				.completeForm(ContactSubject.CUSTOMER_SERVICE, "email@test.com", "order4321", "The product arrived with damages.");

		Assertions.assertThat(contactPage.getSuccessMessage()).isEqualTo("Your message has been successfully sent to our team.");
	}

	public void invalidEmailGiven_shouldShowErrorMessage() {
		final ContactPage contactPage = new ContactPage(driver)
				.get()
				.completeForm(ContactSubject.CUSTOMER_SERVICE, "email", "order4321", "The product arrived with damages.");

		Assertions.assertThat(contactPage.getErrorMessage()).isEqualTo("Invalid email address.");
	}
}
