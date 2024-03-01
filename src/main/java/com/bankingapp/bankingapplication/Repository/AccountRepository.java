package com.bankingapp.bankingapplication.Repository;

import com.bankingapp.bankingapplication.Model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account getByUserUserId(long userId);
}
