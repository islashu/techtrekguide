package Misc.model;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class UserMongo {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
