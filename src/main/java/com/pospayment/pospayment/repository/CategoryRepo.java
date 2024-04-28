package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {
    public List<?> findByCompanyID(Integer companyID);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM categories WHERE uuid = ?1")
    public void deleteByuuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT name FROM categories WHERE uuid = ?1")
    String getCategoryName(String uuid);
}
