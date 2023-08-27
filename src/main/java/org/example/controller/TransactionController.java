package org.example.controller;

import org.example.dao.AccountDao;
import org.example.dao.TransactionDao;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.time.LocalDate;
import java.util.List;

public class TransactionController {
    TransactionDao transactionDao;
    AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    public void createTransaction(Transaction transaction) {
        transactionDao.createTransaction(transaction);
    }

    public Transaction getTransactionById(long id) {
        return transactionDao.getTransaction(id);
    }

    public List<Transaction> getListTransactionByUserId (long accountId) {
       return transactionDao.getListOfUserTransactions(accountId);
    }

    public double doTransfer(long fromAccountId, long toAccountId, double amount) {
        Account fromAccount = accountDao.getAccount(fromAccountId);
        Account toAccount = accountDao.getAccount(toAccountId);

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction transaction = new Transaction(0L, fromAccountId, toAccountId, amount, LocalDate.now(), TransactionType.TRANSFER);
        createTransaction(transaction);

        accountDao.updateAccount(fromAccount);
        accountDao.updateAccount(toAccount);

        return fromAccount.getBalance();
    }

    public double doWithdraw (long fromAccountId, double amount) {
        Account account = accountDao.getAccount(fromAccountId);

        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction(0L, fromAccountId, null, amount, LocalDate.now(), TransactionType.WITHDRAW);

        createTransaction(transaction);

        accountDao.updateAccount(account);

        return account.getBalance();
    }

    public double doDeposit (long toAccountId, double amount) {
        Account account = accountDao.getAccount(toAccountId);

        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction(0L, null, toAccountId, amount, LocalDate.now(), TransactionType.DEPOSIT);

        createTransaction(transaction);

        accountDao.updateAccount(account);

        return account.getBalance();
    }
}
