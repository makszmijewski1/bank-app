package org.example.view;
import org.example.controller.AccountController;
import org.example.controller.UserController;
import org.example.model.Account;
import org.example.model.User;

import java.io.IOException;
import java.time.LocalDate;

import static org.example.view.ConsoleIO.*;

public class CommandLineView implements View{

    private boolean isAuthorize;
    UserController userController;
    AccountController accountController;

    public CommandLineView(UserController userController, AccountController accountController) {
        this.userController = userController;
        this.accountController = accountController;
    }
    @Override
    public void listen() {
        try {
            int choice;
            do {
                println("Java Bank Application");
                println("Type 0 to exit to terminate");
                println("Type 1 to log in or create new account");
                choice = readInt();
                if (choice == 1) {
                if (isAuthorize) {
                    init();
                } else {
                    authorize();
                }}
            }while (choice != 0);
        }catch (IOException e) {
            println("IO Exception Error");
            e.printStackTrace();
        }
    }


    @Override
    public void logIn() {
        String username = null;
        String password = null;
        try {
            print("Enter Your username: ");
            username = readLine();
            print("Enter Your password: ");
            password = readLine();
        }catch (IOException e ) {
            println("IOException");
            e.printStackTrace();
        }

        Account account = accountController.getAccountByUsername(username);

        if (account == null) {
            print("Can't log in");
        } else if (!account.getPassword().equals(password)) {
            print("Can't log in");
        } else {
            init();
        }
    }

    @Override
    public void init(){

    }

    @Override
    public void authorize() {
       try{
           int choice;
           do {
               println("1 - Log in to Account");
               println("2 - Create new Account");
               choice = readInt();
           }while (choice != 1 && choice != 2);

           if (choice == 1 ) {
               logIn();
           } else {
               register();
           }
       }catch (IOException e) {
           println("IOException");
           e.printStackTrace();
       }
    }

    @Override
    public void register() {
        try{
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
        }catch (IOException e) {
            println("IOException");
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {
    }

    @Override
    public void checkBalance() {
        
    }

    @Override
    public void checkHistory() {

    }

    @Override
    public void editAccount() {

    }

    @Override
    public void removeAccount() {

    }

    @Override
    public void withdraw() {

    }

    @Override
    public void deposit() {

    }

    @Override
    public void transfer() {

    }
}
