package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {
    public List<?> findByCompanyID(Integer companyID);
}
