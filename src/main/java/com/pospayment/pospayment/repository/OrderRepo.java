package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, String> {

   List<Order> findByCompany(Company company);
}
