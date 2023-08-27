package org.example.dao;

import org.example.model.Account;

import java.sql.*;
import java.time.LocalDate;

public class AccountDao extends Dao{

    public AccountDao() {
        super.connect();
    }
    public void createAccount(Account account) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO account (balance, username," +
                    " password, createDate, userId) VALUES (?, ?, ?, ?, ?)");
            statement.setLong(1, 0);
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setDate(4, Date.valueOf(account.getCreateDate()));
            statement.setLong(5, account.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(long id) {
        Account account = null;

        try{
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM account WHERE id = " + id);
            result.next();
            Double balance = result.getDouble("balance");
            String username = result.getString("username");
            String password = result.getString("password");
            LocalDate date = result.getDate("createDate").toLocalDate();
            Long userId = result.getLong("userId");
            account = new Account(id, userId, balance, username, password, date);
        } catch (SQLException e) {
        }
        return account;
    }

    public Account getAccountByUsername(String input) {
        Account account = null;

        try{
            PreparedStatement statement = connect.prepareStatement("SELECT * FROM account WHERE username = ?");
            statement.setString(1, input);
            ResultSet result = statement.executeQuery();
            result.next();
            Long id = result.getLong("id");
            Double balance = result.getDouble("balance");
            String username = result.getString("username");
            String password = result.getString("password");
            LocalDate date = result.getDate("createDate").toLocalDate();
            Long userId = result.getLong("userId");
            account = new Account(id, userId, balance, username, password, date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean updateAccount (Account tmpAccount) {
        Account account = getAccount(tmpAccount.getId());
        if (tmpAccount.getUserId() != null) account.setUserId(tmpAccount.getUserId());
        if (tmpAccount.getBalance() != null) account.setBalance(tmpAccount.getBalance());
        if (tmpAccount.getUsername() != null) account.setUsername(tmpAccount.getUsername());
        if (tmpAccount.getPassword() != null) account.setPassword(tmpAccount.getPassword());
        if (tmpAccount.getCreateDate() != null) account.setCreateDate(tmpAccount.getCreateDate());

        try {
            PreparedStatement statement = connect.prepareStatement("UPDATE account SET balance = ?, username = ?, " +
                    "password = ?, createDate = ?, userId = ? WHERE id = ?");
            statement.setDouble(1, account.getBalance());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setDate(4, Date.valueOf(account.getCreateDate()));
            statement.setLong(5, account.getUserId());
            statement.setLong(6, account.getId());
            return statement.execute();
        }catch (SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount (long id) {
        PreparedStatement statement = null;
        try {
            statement = connect.prepareStatement("DELETE FROM account WHERE ID = ?");
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e ) {
            e.printStackTrace();
            return false;
        }
    }
}
