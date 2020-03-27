package org.apoos.service;

import org.apoos.dao.BranchDao;
import org.apoos.model.Branch;

import java.util.List;

public class BranchService {
    private static BranchDao branchDao;

    public  BranchService(){
        branchDao = new BranchDao();
    }

    public static void setBranchDao(BranchDao branchDao) {
        BranchService.branchDao = branchDao;
    }

    public void persist(Branch branch) {
        branchDao.openCurrentSessionWithTransaction();
        branchDao.persist(branch);
        branchDao.closeCurrentSessionWithTransaction();
    }

    public void update(Branch branch) {
        branchDao.openCurrentSessionWithTransaction();
        branchDao.update(branch);
        branchDao.closeCurrentSessionWithTransaction();
    }

    public Branch findById(int id){
        branchDao.openCurrentSession();
        Branch branch = (Branch) branchDao.findById(id);
        branchDao.closeCurrentSession();
        return  branch;
    }

    public void delete(int id) {
        branchDao.openCurrentSessionWithTransaction();
        Branch branch = branchDao.findById(id);
        branchDao.delete(branch);
        branchDao.closeCurrentSessionWithTransaction();
    }

    public List<Branch> findAll() {
        branchDao.openCurrentSession();
        List<Branch> branches = branchDao.findAll();
        branchDao.closeCurrentSession();
        return branches;
    }

    public void deleteAll() {
        branchDao.openCurrentSessionWithTransaction();
        branchDao.deleteAll();
        branchDao.closeCurrentSessionWithTransaction();
    }

    public static BranchDao getBranchDao() {
        return branchDao;
    }
}
