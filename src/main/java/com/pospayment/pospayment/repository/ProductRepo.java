package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    public List<Product> findByCategory(String category);


}
