package org.apoos.dao;

import org.apoos.hibernate.HibernateUtil;
import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BranchDao {
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

    public void persist(Branch branch) {
        getCurrentSession().saveOrUpdate(branch);
    }

    public void update(Branch branch) {
        getCurrentSession().update(branch);
    }

    public Branch findById(Integer id) {
        Branch branch = (Branch) getCurrentSession().get(Branch.class, id);
        Hibernate.initialize(branch.getAccounts());
        return branch;
    }

    public void delete(Branch branch) {
        getCurrentSession().delete(branch);
    }

    public List<Branch> findAll() {
        List<Branch> branches = (List<Branch>) getCurrentSession().createQuery("from Branch").list();
        return branches;
    }

    public void deleteAll() {
        List<Branch> branches = findAll();
        for (Branch branch : branches) {
            delete(branch);
        }
    }

}
