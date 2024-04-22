package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    public List<Product> findByCategory(String category);


    public List<Product> findByCompanyID(Integer companyID);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM products WHERE uuid = ?1")
    public void deleteByUUID(String uuid);
}
