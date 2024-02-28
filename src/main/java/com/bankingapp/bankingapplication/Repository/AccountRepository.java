package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{


    public Account findByUserId(Integer userId);
}
