package com.bankingapp.bankingapplication.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLInsert;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Entity // maps user obj to db
@Table(name="account") // tells that the db name is user
@Data // tells use to make setters and getters implicitly
@AllArgsConstructor // implicitly creates all args constructor
@NoArgsConstructor // implicitly creates no args constructor
@Getter
@Setter
//@Configuration
public class Account {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int accountId;
    @ColumnDefault("0.00")
    private double balance;
    private Integer userId;

    public double setDeposit(double amount){
        return this.balance += amount;
    }

    public double setWithdraw(double amount) {

        if (amount < this.balance)
            this.balance -= amount;
        return this.balance;
    }


}
