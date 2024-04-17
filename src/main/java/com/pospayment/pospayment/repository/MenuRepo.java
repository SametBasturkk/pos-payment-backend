package com.pospayment.pospayment.repository;

import com.pospayment.pospayment.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<Menu, String> {
}
