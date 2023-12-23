package com.vasanth.springcloud.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vasanth.springcloud.model.OrderDetails;
import com.vasanth.springcloud.repository.OrderRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	private final OrderRepo orderRepo;

	@Autowired
	public OrderService(OrderRepo orderRepository) {
		this.orderRepo = orderRepository;
	}

	@Transactional
	public void saveOrdersFromJson(String json) {
		try {
			// Use Jackson ObjectMapper to deserialize JSON into a list of OrderDetails
			ObjectMapper objectMapper = new ObjectMapper();
			List<OrderDetails> orderDetailsList = objectMapper.readValue(json,
					objectMapper.getTypeFactory().constructCollectionType(List.class, OrderDetails.class));

			// Iterate through the list and save each order to the database
			for (OrderDetails orderDetails : orderDetailsList) {
				// You can perform any additional processing or validation here before saving
				String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
				orderDetails.setOrderedDate(currentDate);
				orderRepo.save(orderDetails);
			}
		} catch (Exception e) {
			// Handle the exception (e.g., log it or throw a custom exception)
			e.printStackTrace();
			throw new RuntimeException("Failed to save orders from JSON: " + e.getMessage());
		}
	}
	
	public List<OrderDetails> getOrdersByCustomerId(Long customerId) {
        // Implement logic to fetch orders by customer ID from the repository
        return orderRepo.findByCustomerId(customerId);
    }
}