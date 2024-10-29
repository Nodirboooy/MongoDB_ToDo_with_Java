package org.example.model;
import lombok.Data;
import org.bson.types.ObjectId;

@Data

public class User {
    private ObjectId id;
    private String name;
    private String surname;
    private String password;

    public User(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }
}
