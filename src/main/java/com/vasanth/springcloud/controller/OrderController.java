package com.vasanth.springcloud.controller;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vasanth.springcloud.email.ExcelExporter;
import com.vasanth.springcloud.model.OrderDetails;
import com.vasanth.springcloud.service.EmailService;
import com.vasanth.springcloud.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	private final EmailService emailService;

	@Autowired
	public OrderController(OrderService orderService, EmailService emailService) {
		this.orderService = orderService;
		this.emailService = emailService;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createOrders(@RequestBody String json) {
		try {
			orderService.saveOrdersFromJson(json);
			return new ResponseEntity<>("Orders created successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed to create orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/{customerId}/export")
	public ResponseEntity<String> exportOrdersToExcelAndSendEmail(@PathVariable Long customerId) {
		try {
			// Fetch orders based on customer ID
			List<OrderDetails> orders = orderService.getOrdersByCustomerId(customerId);

			// Generate Excel sheet
			Workbook workbook = ExcelExporter.exportToExcel(orders);

			// Send email with the generated Excel sheet attached
			emailService.sendEmailWithAttachment(customerId, workbook);

			return ResponseEntity.ok("Order details exported to Excel and email sent successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error exporting orders to Excel and sending email: " + e.getMessage());
		}
	}
}