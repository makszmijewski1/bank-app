package org.example;

import org.example.controller.AccountController;
import org.example.controller.TransactionController;
import org.example.controller.UserController;
import org.example.dao.AccountDao;
import org.example.dao.TransactionDao;
import org.example.dao.UserDao;
import org.example.view.CommandLineView;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args){
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();
        TransactionDao transactionDao = new TransactionDao();
        UserController userController = new UserController(userDao);
        AccountController accountController = new AccountController(accountDao);
        TransactionController transactionController = new TransactionController(transactionDao, accountDao);
        CommandLineView commandLineView = new CommandLineView(userController, accountController, transactionController);


        commandLineView.listen();
    }
}