package com.bankingapp.bankingapplication.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity // maps user obj to db
@Table(name="account") // tells that the db name is user
@Data // tells use to make setters and getters implicitly
@AllArgsConstructor // implicitly creates all args constructor
@NoArgsConstructor // implicitly creates no args constructor
@Getter
@Setter
@Builder
//@Configuration
public class Account {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private long accountId;

    @ColumnDefault("0.00")
    private double balance;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user; // foreign key referencing the user table, an account belongs to one user
}
