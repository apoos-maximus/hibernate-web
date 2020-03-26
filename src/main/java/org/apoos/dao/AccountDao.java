package org.apoos.dao;

import org.apoos.hibernate.HibernateUtil;
import org.apoos.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountDao {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Account account) {
        getCurrentSession().saveOrUpdate(account.getAccountBranch());
        getCurrentSession().save(account);
    }

    public void update(Account account) {
        getCurrentSession().saveOrUpdate(account.getAccountBranch());
        getCurrentSession().update(account);
    }

    public Account findById(Integer id) {
        Account account = (Account) getCurrentSession().get(Account.class, id);
        return account;
    }

    public void delete(Account account) {
        getCurrentSession().delete(account);
    }

    public List<Account> findAll() {
        List<Account> accounts = (List<Account>) getCurrentSession().createQuery("from Account").list();
        return accounts;
    }

    public void deleteAll() {
        List<Account> accounts = findAll();
        for (Account account : accounts) {
            delete(account);
        }
    }

}