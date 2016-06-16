package builders;

public class Address {
	private final String street;
	private final String housenumber;
	private final String postcode;
	private final String country;

	private Address(final AddressBuilder builder) {
		this.street = builder.street;
		this.housenumber = builder.housenumber;
		this.postcode = builder.postcode;
		this.country = builder.country;
	}

	public String getStreet() {
		return street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", housenumber=" + housenumber + ", postcode=" + postcode + ", country=" + country + "]";
	}

	public static class AddressBuilder {
		private String street;
		private String housenumber;
		private String postcode;
		private String country;

		public AddressBuilder(final String street, final String postcode) {
			this.street = street;
			this.postcode = postcode;
		}

		public AddressBuilder withHousenumber(final String housenumber) {
			this.housenumber = housenumber;
			return this;
		}

		public AddressBuilder withCountry(final String country) {
			this.country = country;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}

}
