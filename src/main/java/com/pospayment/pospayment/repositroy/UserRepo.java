package com.pospayment.pospayment.repositroy;

import com.pospayment.pospayment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
    public User findByUsername(String username);
}
