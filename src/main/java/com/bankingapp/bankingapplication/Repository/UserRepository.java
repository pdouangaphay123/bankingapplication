package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{


    public User findByEmail(String email);
}
