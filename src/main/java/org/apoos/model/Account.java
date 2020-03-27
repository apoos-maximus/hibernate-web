package org.apoos.model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;

import javax.persistence.*;

@Entity
@Table(name = "account_table")
public class Account {

    @Id
    @Column(name = "acNumber")
    private int accountNumber;

    @Column(name = "acHolderName")
    private String holderName;

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

    public Branch getAccountBranch() {
        return accountBranch;
    }

    @Override
    public String toString() {
        JsonObject jso = new JsonObject();
        jso.addProperty("accountNumber",Integer.toString(getAccountNumber()));
        jso.addProperty("holderName",getHolderName());
        jso.addProperty("accountBranch",getAccountBranch().toString());

        return  jso.toString().replaceAll("\\\\","").replaceAll("\"","{");
    }
}
