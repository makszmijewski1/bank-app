package org.example;

import org.example.controller.AccountController;
import org.example.controller.UserController;
import org.example.dao.AccountDao;
import org.example.dao.TransactionDao;
import org.example.dao.UserDao;
import org.example.view.CommandLineView;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        AccountDao accountDao = new AccountDao();
        TransactionDao transactionDao = new TransactionDao();
        UserController userController = new UserController(userDao);
        AccountController accountController = new AccountController(accountDao);

        CommandLineView commandLineView = new CommandLineView(userController, accountController);
//        User user = userDao.getUser(1);
//        System.out.println(user);
//
//        User user2 = new User(0L, "Maxime", 26, 8128231L, null);
//        userDao.createUser(user2);

//        User user7 = new User();
//        user7.setId(1L);
//        user7.setAge(26);
//        user7.setName("Paulina");
//
//        userDao.updateUser(user7);
//
        //   Account account = new Account(0L, 2L, 0.0, "Maxime", "password", LocalDate.now());
//        Account account2 = new Account(0L, 1L, 0.0, "Paulina", "password", LocalDate.now());
//
//        Account account3 = new Account(3L, null, 5000.0, null, null, null);
//
  //      accountDao.createAccount(account);


//   Transaction transaction1 = new Transaction(0L, 3L, 4L, 200.0, LocalDate.now(), TransactionType.TRANSFER);
//   Transaction transaction2 = new Transaction(0L, 3L, 4L, 500.0, LocalDate.now(), TransactionType.TRANSFER);
//   Transaction transaction3 = new Transaction(0L, 3L, 4L, 400.0, LocalDate.now(), TransactionType.TRANSFER);
//
//   transactionDao.createTransaction(transaction1);
//   transactionDao.createTransaction(transaction2);
//   transactionDao.createTransaction(transaction3);



//    Transaction transaction = transactionDao.getTransaction(1);
//
//
//
//        List<Transaction> transactions = transactionDao.getListOfUserTransactions(3L);
//
//        for (Transaction transaction1 : transactions) {
//            System.out.println(transaction1);
//        }

        commandLineView.listen();

    }
}