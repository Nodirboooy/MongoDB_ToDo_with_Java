package org.example.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import org.example.model.User;

public class UserRepository {
    private final MongoCollection<Document> userCollection;

    public UserRepository(MongoDatabase database) {
        this.userCollection = database.getCollection("users");
    }

    public void addUser(User user) {
        Document document = new Document()
                .append("name", user.getName())
                .append("password", user.getPassword());
        userCollection.insertOne(document);
        System.out.println("Foydalanuvchi "+  user.getName()+" qo'shildi: " );
    }




}
