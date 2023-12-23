package com.vasanth.springcloud.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailWithAttachment(Long customerId, Workbook workbook) throws MessagingException, IOException {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("vasanth13061999@gmail.com");
			helper.setTo("fullstackdevfrom22@gmail.com");
			helper.setSubject("Order Details for Customer ID: " + customerId);
			helper.setText("Please find attached order details.");

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byte[] attachmentData = bos.toByteArray();
			// Attach the file
			helper.addAttachment("order_details.xlsx", new ByteArrayResource(attachmentData));

			// Send the email
			javaMailSender.send(message);
			System.out.println("Email sent successfully!");
		} catch (MessagingException e) {
			// Log the exception and handle it appropriately
			e.printStackTrace();
			System.err.println("Error sending email: " + e.getMessage());
			throw e; // Re-throw the exception to let the caller handle it
		}
	}
}