package org.apoos.service;

import org.apoos.dao.AccountDao;
import org.apoos.model.Account;

import java.util.List;

public class AccountService {
    private static AccountDao accountDao;

    public  AccountService(){
        accountDao = new AccountDao();
    }

    public void persist(Account account) {
        accountDao.openCurrentSessionWithTransaction();
        accountDao.persist(account);
        accountDao.closeCurrentSessionWithTransaction();
    }

    public void update(Account account) {
        accountDao.openCurrentSessionWithTransaction();
        accountDao.update(account);
        accountDao.closeCurrentSessionWithTransaction();
    }

    public Account findById(int id){
        accountDao.openCurrentSession();
        Account aco = (Account) accountDao.findById(id);
        accountDao.closeCurrentSession();
        return  aco;
    }

    public void delete(int id) {
        accountDao.openCurrentSessionWithTransaction();
        Account account = accountDao.findById(id);
        accountDao.delete(account);
        accountDao.closeCurrentSessionWithTransaction();
    }

    public List<Account> findAll() {
        accountDao.openCurrentSession();
        List<Account> accounts = accountDao.findAll();
        accountDao.closeCurrentSession();
        return accounts;
    }

    public void deleteAll() {
        accountDao.openCurrentSessionWithTransaction();
        accountDao.deleteAll();
        accountDao.closeCurrentSessionWithTransaction();
    }

    public static AccountDao getAccountDao() {
        return accountDao;
    }
}
