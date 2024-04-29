package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE uuid = ?1")
    Order findByUuid(String uuid);

    Object findByCompany(Company company);
}
