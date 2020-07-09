package org.apoos.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "account_table")
public class Account {

    @Id
    @Column(name = "acNumber")
    private int accountNumber;

    @Column(name = "acHolderName")
    private String holderName;

    @Column(name = "acBalance")
    private Integer accountBalance;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public Branch getAccountBranch() {
        return accountBranch;
    }

    public void credit(int amount) {
        accountBalance += amount;
    }
    public void debit(int amount) {
        accountBalance -= amount;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return  gson.toJson(this);
    }
}
