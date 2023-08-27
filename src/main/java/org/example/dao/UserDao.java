package org.example.dao;

import org.example.model.User;

import java.sql.*;

public class UserDao extends Dao{

    public UserDao() {
        super.connect();
    }

    public void createUser(User user){
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO users (name, age, pesel) VALUES (?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setLong(3, user.getPesel());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User cannot be created");
        }
    }
    public User getUser(long id) {
        User user = null;
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE id = " + id);
            result.next();
            Long userId = result.getLong("id");
            String userName = result.getString("name");
            Integer userAge = result.getInt("age");
            Long userPesel = result.getLong("pesel");
            user = new User(userId, userName, userAge, userPesel);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User " + id + " not found.");
        }
        return user;
    }

    public boolean updateUser(User tmpUser) {
        User user = getUser(tmpUser.getId());
        if (tmpUser.getName() != null) user.setName(tmpUser.getName());
        if (tmpUser.getAge() != null) user.setAge(tmpUser.getAge());
        if (tmpUser.getPesel() != null) user.setPesel(tmpUser.getPesel());
        try {
            PreparedStatement statement = connect.prepareStatement("UPDATE users SET name = ?, age = ?, pesel = ? WHERE id = ?");
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setLong(3, user.getPesel());
            statement.setLong(4, user.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User not found");
            return false;
        }
    }

    public boolean deleteUser(long id) {
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement("DELETE FROM users WHERE ID = ?");
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User not found");
            return false;
        }
    }

    public boolean isUserExist(long pesel) {
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE pesel = " + pesel);
            return result.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public User getUserByPesel(long pesel) {
        User user = null;
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE pesel = " + pesel);
            result.next();
            Long userId = result.getLong("id");
            String userName = result.getString("name");
            Integer userAge = result.getInt("age");
            Long userPesel = result.getLong("pesel");
            user = new User(userId, userName, userAge, userPesel);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User " + pesel + " not found.");
        }
        return user;
    }
}
