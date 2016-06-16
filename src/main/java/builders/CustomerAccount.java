package builders;

public class CustomerAccount {
	private final String email;
	private final String password;

	private CustomerAccount(final CustomerAccountBuilder builder) {
		this.email = builder.email;
		this.password = builder.password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "CustomerAccount [email=" + email + ", password=" + password + "]";
	}

	public static class CustomerAccountBuilder {
		private final String email;
		private final String password;

		public CustomerAccountBuilder(final String email, final String password) {
			this.email = email;
			this.password = password;
		}

		public CustomerAccount build() {
			return new CustomerAccount(this);
		}
	}

}
