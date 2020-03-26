package org.apoos.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "branch_table")
public class Branch {

    @Id
    @Column(name = "branchCode")
    private int branchCode;

    @Column(name = "branchName")
    private String branchName;

    @OneToMany(mappedBy = "accountBranch")
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public int getBranchCode() {
        return branchCode;
    }

    @Override
    public String toString() {
        return "[ branchCode : " + branchCode + ", branchName : " + branchName + "]" ;
    }
}
