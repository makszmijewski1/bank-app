package org.example.controller;

import org.example.dao.AccountDao;
import org.example.model.Account;

public class AccountController {
    AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void createAccount (Account account) {
        accountDao.createAccount(account);
    }

    public Account getAccount(long id) {
       return accountDao.getAccount(id);
    }

    public boolean updateAccount (Account tmpAccount) {
        return accountDao.updateAccount(tmpAccount);
    }

    public boolean deleteAccount (long id) {
        return accountDao.deleteAccount(id);
    }
}
