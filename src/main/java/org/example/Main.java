package org.example;

import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.example.model.ToDo;
import org.example.model.User;
import org.example.service.ToDoRepository;
import org.example.service.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MongoDatabase database = DatabaseConfig.getDatabase();
        UserRepository userRepository = new UserRepository(database);
        ToDoRepository toDoRepository = new ToDoRepository(database);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ismingizni kiriting:");
        String name = scanner.nextLine();
        System.out.println("Familiyangizni kiriting:");
        String surname = scanner.nextLine();
        System.out.println("Parolingizni kiriting:");
        String password = scanner.nextLine();

        User user = new User(name, surname, password);
        userRepository.addUser(user);

        while (true) {
            System.out.println("1. To-Do qo'shish\n2. To-Do ro'yxatini ko'rish\n3. To-Do qidirish\n4. To-Do o'chirish\n0. Chiqish");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("To-Do nomini kiriting:");
                    String todoName = scanner.nextLine();
                    ToDo todo = new ToDo(ObjectId.get(),todoName, new Date());
                    toDoRepository.addTodo( todo);
                    break;

                case 2:
                    List<ToDo> todos = toDoRepository.getAllTodos();
                    System.out.println(user.getName() + " " + user.getSurname() + "ning to-do ro'yxati:");
                    todos.forEach(t -> System.out.println(t.getName()));
                    break;

                case 3:
                    System.out.println("Qidirilayotgan to-do nomini kiriting:");
                    String searchKeyword = scanner.nextLine();
                    String searchResults = toDoRepository.searchToDoByName(searchKeyword);
                     System.out.println(searchResults);
                    break;

                case 4:
                    System.out.println("O'chirilishi kerak bo'lgan to-do nomini kiriting:");
                    todoName = scanner.nextLine();
                    toDoRepository.deleteTodoByName( todoName);
                    System.out.println("To-do o'chirildi.");
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Noto'g'ri tanlov. Qaytadan urinib ko'ring.");
            }
        }
    }
}
