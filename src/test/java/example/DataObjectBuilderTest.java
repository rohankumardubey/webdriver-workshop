package example;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import builders.Address;
import builders.CustomerAccount;
import builders.Order;

public class DataObjectBuilderTest extends AbstractTestBase {

	@Test
	public void login() {
		final CustomerAccount account = new CustomerAccount.CustomerAccountBuilder("test@test.com", "1qazxsw2").build();

		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("input#email")).sendKeys(account.getEmail());
		driver.findElement(By.cssSelector("input#passwd")).sendKeys(account.getPassword());
		driver.findElement(By.cssSelector("button#SubmitLogin")).click();

		Assertions.assertThat(driver.findElement(By.cssSelector("p.info-account")).getText()).as("sucess message")
				.contains("Welcome to your account.");
	}

	@DataProvider
	public Object[][] getAccounts() {
		return new Object[][]{ //
			{ new CustomerAccount.CustomerAccountBuilder("test@test.com", "1qazxsw2").build() }, //
			{ new CustomerAccount.CustomerAccountBuilder("test@test.com", "1qazxsw2").build() } //
		};
	}

	@Test(dataProvider = "getAccounts")
	public void loginWithDataProvider(final CustomerAccount account) {
		driver.findElement(By.cssSelector(".login")).click();

		driver.findElement(By.cssSelector("input#email")).sendKeys(account.getEmail());
		driver.findElement(By.cssSelector("input#passwd")).sendKeys(account.getPassword());
		driver.findElement(By.cssSelector("button#SubmitLogin")).click();

		Assertions.assertThat(driver.findElement(By.cssSelector("p.info-account")).getText()).as("sucess message")
				.contains("Welcome to your account.");
	}

	@AfterMethod
	public void logOut() {
		driver.findElement(By.cssSelector("a.logout")).click();
	}

	@Test
	public void addressTest() {
		final Order order = new Order.OrderBuilder()//
				.withInvoiceAddress(new Address.AddressBuilder("abc street", "1234ab").build())//
				.withShippingAddress(new Address.AddressBuilder("shipstreet", "4321ab").withCountry("The Netherlands").build())//
				.build();

		// Retrieve data from the object
		System.out.println(order.getInvoiceAddress().getStreet());

		System.out.println(order.getShippingAddress().getCountry());
	}
}
