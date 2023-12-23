package com.vasanth.springcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vasanth.springcloud.model.OrderDetails;

public interface OrderRepo extends JpaRepository<OrderDetails, Long> {

	List<OrderDetails> findByCustomerId(Long customerId);

}
