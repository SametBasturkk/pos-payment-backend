package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Category;
import com.pospayment.pospayment.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepo extends JpaRepository<Category, String> {
    public List<Category> findByCompany(Company company);


}
