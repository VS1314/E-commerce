package com.vasanth.springcloud.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Order_Table")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "customer_id")
	@JsonProperty("customerId")
	private Long customerId;

	@JsonProperty("product")
	private String product;

	@JsonProperty("sellerId")
	@Column(name = "seller_id")
	private Long sellerId;

	@JsonProperty("sellerName")
	@Column(name = "seller_name")
	private String sellerName;

	@JsonProperty("shippingLocation")
	@Column(name = "shipping_location")
	private String shippingLocation;

	@JsonProperty("amount")
	private Double amount;

	@JsonProperty("orderId")
	@Column(name = "order_id")
	private String orderId;

	@JsonProperty("deliveryDate")
	@Column(name = "delivery_date")
	private String deliveryDate;

	@JsonProperty("orderedDate")
	@Column(name = "ordered_date")
	private String orderedDate;

	@JsonProperty("productDescription")
	@Column(name = "product_description")
	private String productDescription;

	@JsonProperty("customerContact")
	@Column(name = "customer_contact")
	private String customerContact;

	@JsonProperty("sellerContact")
	@Column(name = "seller_contact")
	private String sellerContact;

	@JsonProperty("productImage")
	@Column(name = "product_image")
	private String productImage; // Assuming the product image is stored as a string (file path or URL)

	public OrderDetails() {
		// Generate random IDs and order ID during object creation
		this.customerId = generateRandomLong(10);
		this.sellerId = generateRandomLong(9);
		this.orderId = generateRandomOrderId();
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
		// Calculate delivery date as 5 days from ordered date
		LocalDate parsedDate = LocalDate.parse(orderedDate, DateTimeFormatter.ISO_DATE);
		LocalDate calculatedDeliveryDate = parsedDate.plusDays(5);
		this.deliveryDate = calculatedDeliveryDate.format(DateTimeFormatter.ISO_DATE);
	}

	// Helper method to generate a random long of specified length
	private Long generateRandomLong(int length) {
		long min = (long) Math.pow(10, length - 1);
		long max = (long) Math.pow(10, length) - 1;
		return min + (long) (Math.random() * (max - min + 1));
	}

	// Updated helper method to generate a random order ID with uppercase alphabets
	// and numbers
	private String generateRandomOrderId() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder orderIdBuilder = new StringBuilder();

		// Append 1 random uppercase alphabet at the start
		Random random = new Random();
		char startChar = alphabet.charAt(random.nextInt(alphabet.length()));
		orderIdBuilder.append(startChar);

		// Append 5 random digits
		orderIdBuilder.append(generateRandomLong(5));

		// Append 1 random uppercase alphabet in the middle
		char middleChar = alphabet.charAt(random.nextInt(alphabet.length()));
		orderIdBuilder.append(middleChar);

		// Append 5 more random digits
		orderIdBuilder.append(generateRandomLong(5));

		// Append 1 random uppercase alphabet at the end
		char endChar = alphabet.charAt(random.nextInt(alphabet.length()));
		orderIdBuilder.append(endChar);

		return orderIdBuilder.toString();
	}

	public Long getId() {
		return id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getProduct() {
		return product;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public String getShippingLocation() {
		return shippingLocation;
	}

	public Double getAmount() {
		return amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public String getSellerContact() {
		return sellerContact;
	}

	public String getProductImage() {
		return productImage;
	}

}
