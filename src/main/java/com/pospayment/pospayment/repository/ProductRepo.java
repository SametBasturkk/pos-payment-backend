package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, String> {

    List<Product> findByCompany(Company company);

    List<Product> findByCategory(Category category);
}
