package org.apoos.model;


import javax.persistence.*;

@Entity
@Table(name = "account_table")
public class Account {

    @Id
    @Column(name = "acNumber")
    private int accountNumber;

    @Column(name = "acHolderName")
    private String holderName;

    @ManyToOne
    private Branch accountBranch;


    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setAccountBranch(Branch accountBranch) {
        this.accountBranch = accountBranch;
    }

    public Branch getAccountBranch() {
        return accountBranch;
    }

    @Override
    public String toString() {
        return "Account [ accountNumber : " + accountNumber + " , holderName : " + holderName + "]";
    }
}
