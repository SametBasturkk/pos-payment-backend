package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, String> {


    @Query("SELECT m FROM Menu m JOIN FETCH m.categories WHERE m.company = :company")
    List<Menu> findByCompany(@Param("company") Company company);

}
