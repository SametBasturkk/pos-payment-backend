package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE category-uuid = ?1")
    public List<Product> findByCategoryUuid(String category);


    public List<Product> findByCompanyID(Integer companyID);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM products WHERE uuid = ?1")
    public void deleteByUUID(String uuid);
}
