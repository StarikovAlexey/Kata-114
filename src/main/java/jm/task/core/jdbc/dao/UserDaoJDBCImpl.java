package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    private List<User> userList = new ArrayList<>();
    private Statement statement;
    private Connection connection;
    private static ResultSet rs;

    public void createUsersTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS users(ID int NOT NULL AUTO_INCREMENT,PRIMARY KEY (ID), " +
                    "name varchar(255), lastname varchar(255), age tinyint);";
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try {
            String query = "DROP TABLE IF  EXISTS  users;";
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String query = "INSERT Users(name, lastname, age) VALUES ('" + name + "', " +
                    "'" + lastName + "', '" + age + "');";
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {

        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        try {
            String query = "select * from users;";
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
            int count = 0;
            while (rs.next()) {
                userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
                userList.get(count).setId(rs.getLong(1));
                count++;
            }
            userList.forEach(x -> System.out.println(x.toString()));
            statement.close();
            Util.closeConnection();

        } catch (SQLException e) {

        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            String query = "TRUNCATE TABLE users;";
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.execute(query);
            rs = statement.getResultSet();
            statement.close();
            Util.closeConnection();
        } catch (SQLException e) {

        }
    }
}