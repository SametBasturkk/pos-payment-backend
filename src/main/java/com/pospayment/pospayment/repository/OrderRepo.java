package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, String>{
}