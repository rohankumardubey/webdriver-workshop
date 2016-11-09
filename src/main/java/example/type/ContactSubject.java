package example.type;

public enum ContactSubject {
	CUSTOMER_SERVICE("Customer service"), WEBMASTER("Webmaster");

	private final String contactSubject;

	private ContactSubject(final String contactSubject) {
		this.contactSubject = contactSubject;
	}

	@Override
	public String toString() {
		return contactSubject;
	}
}
