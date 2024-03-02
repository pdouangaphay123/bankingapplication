package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Integer>{

    User findByEmail(String email);
}

