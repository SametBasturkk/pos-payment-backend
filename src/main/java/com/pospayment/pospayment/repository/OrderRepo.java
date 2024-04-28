package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, String> {


    List<Order> findByCompanyID(Integer companyId);

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE uuid = ?1")
    Order findByUuid(String uuid);
}
