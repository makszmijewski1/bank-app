package org.example.dao;

import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TransactionDao extends Dao{

    public TransactionDao() {
        super.connect();
    }

    public void createTransaction (Transaction transaction) {
        try {
            PreparedStatement statement = connect.prepareStatement("INSERT INTO transactions (fromAccountId, " +
                    "toAccountId, amount, transactionDate, type) VALUES (?, ?, ?, ?, ?)");
            statement.setLong(1, transaction.getFromAccountId());
            statement.setLong(2, transaction.getToAccountId());
            statement.setDouble(3, transaction.getAmount());
            statement.setDate(4, Date.valueOf(transaction.getDate()));
            statement.setString(5, transaction.getTransactionType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot create transaction");
        }
    }

    public Transaction getTransaction(long id) {
        Transaction transaction = null;
        try{
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transactions WHERE id = " + id);
            result.next();
            Long fromAccountId = result.getLong("fromAccountId");
            Long toAccountId = result.getLong("toAccountId");
            Double amount = result.getDouble("amount");
            LocalDate date = result.getDate("transactionDate").toLocalDate();
            TransactionType type = TransactionType.valueOf(result.getString("type").toUpperCase());

            transaction = new Transaction(id, fromAccountId, toAccountId, amount, date, type);
        }catch (SQLException e ) {
            e.printStackTrace();
            System.out.println("Cannot get transaction");
        }
        return transaction;
    }

    public List<Transaction> getListOfUserTransactions (Long accountId) {
            ArrayList<Transaction> outgoingTransactions = getOutgoingTransactions(accountId);
            ArrayList<Transaction> incomingTransactions = getIncomingTransactions(accountId);

            return Stream.concat(outgoingTransactions.stream(), incomingTransactions.stream())
                    .sorted(Comparator.comparing(Transaction::getDate))
                    .toList();
    }


    private ArrayList<Transaction> getOutgoingTransactions(Long accountId) {
           ArrayList<Transaction> outgoingTransactions = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transactions WHERE fromAccountId = " + accountId);
            while(result.next()) {
                Long fromAccountId = result.getLong("fromAccountId");
                Long toAccountId = result.getLong("toAccountId");
                Double amount = result.getDouble("amount");
                LocalDate date = result.getDate("transactionDate").toLocalDate();
                TransactionType type = TransactionType.valueOf(result.getString("type").toUpperCase());
                Transaction transaction = new Transaction(accountId, fromAccountId, toAccountId, amount, date, type);
                outgoingTransactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot get list of user transactions");
        }
        return outgoingTransactions;
    }

    private ArrayList<Transaction> getIncomingTransactions(Long accountId) {
        ArrayList<Transaction> incomingTransactions = new ArrayList<>();
        try {
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM transactions WHERE toAccountId = " + accountId);
            while(result.next()) {
                Long fromAccountId = result.getLong("fromAccountId");
                Long toAccountId = result.getLong("toAccountId");
                Double amount = result.getDouble("amount");
                LocalDate date = result.getDate("transactionDate").toLocalDate();
                TransactionType type = TransactionType.valueOf(result.getString("type").toUpperCase());
                Transaction transaction = new Transaction(accountId, fromAccountId, toAccountId, amount, date, type);
                incomingTransactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Cannot get list of user transactions");
        }
        return incomingTransactions;
    }

}
