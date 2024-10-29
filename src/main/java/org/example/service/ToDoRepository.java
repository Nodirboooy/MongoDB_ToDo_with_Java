package org.example.service;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.example.model.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoRepository {
    private MongoCollection<Document> todoCollection;



    public  ToDoRepository(MongoDatabase database) {
        this.todoCollection = database.getCollection("todos");
    }

    public void addTodo(ToDo todo) {
        Document document = new Document("name", todo.getName())
                .append("dateAdded", todo.getDateAdded());
        todoCollection.insertOne(document);
        System.out.println("To-Do qo'shildi: " + todo.getName());
    }

    public List<ToDo> getAllTodos() {
        List<ToDo> todos = new ArrayList<>();
        for (Document doc : todoCollection.find()) {
            String name = doc.getString("name");
            ToDo todo = new ToDo();
            todos.add(todo);
        }
        return todos;
    }

    public String searchToDoByName(String searchKeyword) {
        todoCollection.find(Filters.regex("name", ".*" + searchKeyword + ".*"));
        System.out.println(searchKeyword+" nomli To-Do topildi: " );
        return searchKeyword;
    }



    public void deleteTodoByName(String todoName) {
        todoCollection.deleteOne(Filters.eq("name", todoName));
        System.out.println(todoName+" nomli To-Do o'chirildi: " );
    }


}

