package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompanyRepo extends JpaRepository<Company, String> {

    public List<Company> findAll();
}
