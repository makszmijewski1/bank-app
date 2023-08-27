package org.example.view;

import org.example.controller.AccountController;
import org.example.controller.TransactionController;
import org.example.controller.UserController;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.User;
import java.time.LocalDate;
import java.util.List;

import static org.example.view.ConsoleIO.*;

public class CommandLineView implements View {
    private long loggedAccountId;
    private UserController userController;
    private AccountController accountController;

    private TransactionController transactionController;

    public CommandLineView(UserController userController, AccountController accountController, TransactionController transactionController) {
        this.userController = userController;
        this.accountController = accountController;
        this.transactionController = transactionController;
    }

    @Override
    public void listen() {

        Integer choice = 0;
        do {
            println("Java Bank Application");
            println("Type 0 to exit to terminate");
            println("Type 1 to log in or create new account");
            choice = readInt();

            if (choice == null)
                choice = -1;

            if (choice == 1) {
                if (loggedAccountId != 0) {
                    init();
                } else {
                    authorize();
                }
            }

        } while (choice != 0);
    }


    @Override
    public void logIn() {
        String username;
        String password;

        print("Enter Your username: ");
        username = readLine();
        print("Enter Your password: ");
        password = readLine();

        Account account = accountController.getAccountByUsername(username);

        if (account == null) {
            println("Can't log in");
        } else if (!account.getPassword().equals(password)) {
            println("Can't log in");
        } else {
            loggedAccountId = account.getId();
            init();
        }
    }

    @Override
    public void init() {
        Integer choice = 0;

        do {
            println("Choose operation");
            println("1 - check balance");
            println("2 - check history");
            println("3 - withdraw");
            println("4 - deposit");
            println("5 - transfer");
            println("6 - edit account");
            println("7 - remove account");
            println("8 - exit");
            choice = readInt();

            if (choice == null)
                choice = 100;

            switch (choice) {
                case 1 -> checkBalance();
                case 2 -> checkHistory();
                case 3 -> withdraw();
                case 4 -> deposit();
                case 5 -> transfer();
                case 6 -> editAccount();
                case 7 -> {
                    removeAccount();
                    loggedAccountId = 0;
                    choice = 8;
                }
                case 8 -> exit();
            }

        } while (choice != 8);
    }

    @Override
    public void authorize() {
        Integer choice;
        do {
            println("1 - Log in to Account");
            println("2 - Create new Account");
            choice = readInt();

            if (choice == null)
                choice = 3;

        } while (choice != 1 && choice != 2);

        if (choice == 1) {
            logIn();
        } else {
            register();
        }
    }

    @Override
    public void register() {
        String name, password, username;
        int age;
        Long pesel;

        println("Create user");
        print("Enter Your name: ");
        name = readLine();
        print("Enter Your age: ");
        age = readInt();
        print("Enter Your pesel: ");
        pesel = readLong();
        print("Enter Your username: ");
        username = readLine();
        print("Enter Your password: ");
        password = readLine();
        User user;
        Account account;
        if (!userController.isUserExist(pesel)) {
            user = new User(null, name, age, pesel);
            userController.createUser(user);
            User createdUser = userController.getUserByPesel(pesel);
            account = new Account(null, createdUser.getId(), 0., username, password, LocalDate.now());
            accountController.createAccount(account);
            println("User create!");
        }
    }

    @Override
    public void exit() {
        loggedAccountId = 0;
    }

    @Override
    public void checkBalance() {
        Account account = accountController.getAccount(loggedAccountId);
        println(account.getUsername() + " current balance: " + account.getBalance());
    }

    @Override
    public void checkHistory() {
        List<Transaction> listTransactionByUserId = transactionController.getListTransactionByUserId(loggedAccountId);

        for (Transaction transaction : listTransactionByUserId) {
            System.out.println(transaction);
        }
    }

    @Override
    public void editAccount() {
        println("Change account information");
        print("Enter current password: ");
        String currentPassword = readLine();
        print("New Password: ");
        String newPassword = readLine();

        if (currentPassword.equals(accountController
                .getAccount(loggedAccountId)
                .getPassword())) {
            Account newAccount = new Account(loggedAccountId, null, null, null, newPassword, null);
            accountController.updateAccount(newAccount);
        } else {
            println("Wrong current password");
        }

    }

    @Override
    public void removeAccount() {
        accountController.deleteAccount(loggedAccountId);
    }

    @Override
    public void withdraw() {
        print("Enter amount: ");
        Double amount = readDouble();
        Account account = accountController.getAccount(loggedAccountId);
        if (amount == null) println("Wrong amount");
        else if (amount < 0) println("Wrong amount");
        else if (amount > account.getBalance()) println("Lack of account funds");
        else transactionController.doWithdraw(loggedAccountId, amount);
    }

    @Override
    public void deposit() {
        print("Enter amount: ");
        Double amount = readDouble();

        if (amount == null) println("Wrong amount");
        else if (amount < 0) println("Wrong amount");
        else transactionController.doDeposit(loggedAccountId, amount);
    }

    @Override
    public void transfer() {
        print("Enter amount: ");
        Double amount = readDouble();

        print("Enter receiver account ID: ");
        Long receiverAccountId = readLong();
        Account account = accountController.getAccount(loggedAccountId);
        if (amount == null) println("Wrong amount");
        else if (amount < 0) println("Wrong amount");
        else if (receiverAccountId == null) println("Wrong ID");
        else if (accountController.getAccount(receiverAccountId) == null) println("Wrong account ID");
        else if (amount > account.getBalance()) println("Lack of account funds");
        else transactionController.doTransfer(loggedAccountId, receiverAccountId, amount);
    }
}
