package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer>{

    public User findByEmail(String email);
    public User findByPassword(String password);
}
