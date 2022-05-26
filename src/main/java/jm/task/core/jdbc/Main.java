package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Alan", "Star", (byte) 36));
        userList.add(new User("Josh", "Felp", (byte) 35));
        userList.add(new User("Mike", "Poop", (byte) 24));
        userList.add(new User("Jake", "Vase", (byte) 22));
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();
        userList.forEach(x -> service.saveUser(x.getName(), x.getLastName(), x.getAge()));
        service.getAllUsers();
        service.removeUserById(2);
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}