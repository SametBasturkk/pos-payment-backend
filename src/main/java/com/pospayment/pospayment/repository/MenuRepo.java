package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, String> {

    @Query("SELECT m FROM Menu m WHERE m.companyID = ?1")
    List<Menu> findByCompanyId(Integer companyId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM menus WHERE uuid = ?1")
    void deleteByUUID(String uuid);
}
