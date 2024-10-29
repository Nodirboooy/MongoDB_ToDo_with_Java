package org.example.model;
;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToDo {
    private ObjectId id;
    private String name;
    private Date dateAdded;


    public ToDo(String name) {
        this.name = name;
        this.dateAdded = new Date();
    }
}
