package org.apoos.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

    @OneToMany(mappedBy = "accountBranch", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> a){
        this.accounts = a;
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
        JsonArray jso = new JsonArray();
        JsonObject brc = new JsonObject();
        String accList;
        for(int i = 0; i < accounts.size(); i++){
            jso.add(accounts.get(i).getAccountNumber());
        }
        brc.addProperty("branchCode",getBranchCode());
        brc.addProperty("branchName",getBranchName());
        brc.addProperty("accounts",jso.toString());

        return brc.toString().replaceAll("\\\\","").replaceAll("\\\"","");
    }
}
