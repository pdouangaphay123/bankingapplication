package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    public Account findByUserId(int userId);
}
