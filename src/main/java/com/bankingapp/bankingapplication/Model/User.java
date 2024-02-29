package com.bankingapp.bankingapplication.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity // maps user obj to db
@Table(name="user") // tells that the db name is user
@Data // tells use to make setters and getters implicitly
@AllArgsConstructor // implicitly creates all args constructor
@NoArgsConstructor // implicitly creates no args constructor
@Getter
@Setter
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int userId;
    @Column(unique=true)
    private String email;
    private String password;
    private String customerName;

//    @OneToOne
//    @JoinColumn(name="user_id")
//    private Account account;


}
