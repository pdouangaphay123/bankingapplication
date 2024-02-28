package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // maps user obj to db
@Table(name="user") // tells that the db name is user
@Data // tells use to make setters and getters implicitly
@AllArgsConstructor // implicitly creates all args constructor
@NoArgsConstructor // implicitly creates no args constructor
@Getter
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int userId;
    private String email;
    private String password;


}
