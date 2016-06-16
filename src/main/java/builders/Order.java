package builders;

public class Order {
	private final Address shippingAddress;
	private final Address invoiceAddress;

	private Order(final OrderBuilder builder) {
		this.shippingAddress = builder.shippingAddress;
		this.invoiceAddress = builder.invoiceAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public Address getInvoiceAddress() {
		return invoiceAddress;
	}

	@Override
	public String toString() {
		return "Order [shippingAddress=" + shippingAddress + ", invoiceAddress=" + invoiceAddress + "]";
	}

	public static class OrderBuilder {
		private Address shippingAddress;
		private Address invoiceAddress;

		public OrderBuilder withShippingAddress(final Address shippingAddress) {
			this.shippingAddress = shippingAddress;
			return this;
		}

		public OrderBuilder withInvoiceAddress(final Address invoiceAddress) {
			this.invoiceAddress = invoiceAddress;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

}
