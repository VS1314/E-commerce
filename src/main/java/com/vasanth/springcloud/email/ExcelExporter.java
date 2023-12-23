package com.vasanth.springcloud.email;

import com.vasanth.springcloud.model.OrderDetails;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExcelExporter {

	public static Workbook exportToExcel(List<OrderDetails> orders) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Orders");

		// Create header row
		Row headerRow = sheet.createRow(0);
		String[] headers = { "Order ID", "Product", "Amount", "Delivery Date", "Customer Id", "Seller Id",
				"Seller Name", "Shipping Location", "Ordered Date", "Product Description", "Customer Contact",
				"Seller Contact", "Product Image" };
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
		}

		// Populate data rows
		int rowNum = 1;
		for (OrderDetails order : orders) {
			Row row = sheet.createRow(rowNum++);
			// Set values based on your OrderDetails fields
			row.createCell(0).setCellValue(order.getOrderId());
			row.createCell(1).setCellValue(order.getProduct());
			row.createCell(2).setCellValue(order.getAmount());
			row.createCell(3).setCellValue(order.getDeliveryDate());
			row.createCell(4).setCellValue(order.getCustomerId());
			row.createCell(5).setCellValue(order.getSellerId());
			row.createCell(6).setCellValue(order.getSellerName());
			row.createCell(7).setCellValue(order.getShippingLocation());
			row.createCell(8).setCellValue(order.getOrderedDate());
			row.createCell(9).setCellValue(order.getProductDescription());
			row.createCell(10).setCellValue(order.getCustomerContact());
			row.createCell(11).setCellValue(order.getSellerContact());
			row.createCell(12).setCellValue(order.getProductImage());
		}

		return workbook;
	}
}
