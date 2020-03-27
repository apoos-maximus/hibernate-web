package org.apoos.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apoos.dao.AccountDao;
import org.apoos.model.Account;
import org.apoos.service.AccountService;
import org.hibernate.Hibernate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class AccountRESTController {

    private AccountService accountService;
    private Gson gson;
    public AccountRESTController(){

    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public ResponseEntity<String> all(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<Account> all;
        all = accountService.findAll();
        System.out.println(all);
        String resp = gson.toJson(all);
        return new ResponseEntity<String>(resp,responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<String> getById(@PathVariable String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (!id.matches("[0-9].*")) return new ResponseEntity<String>("bad request",responseHeaders,HttpStatus.BAD_REQUEST);
        Account account ;
        account = accountService.findById(Integer.parseInt(id)) ;
        String resp = gson.toJson(account);
        return new ResponseEntity<String>(resp,responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody String reqBody){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        Account account;
        account = gson.fromJson(reqBody,Account.class);
        String resp = new String();
        try {
            accountService.persist(account);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp = "{status: account with same id already exists}";
        }
            resp = "{success}";

        return new ResponseEntity<String>(resp,responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping("/account")
    public ResponseEntity<String> editAccount(@RequestBody String reqBody){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        Account account;
        account = gson.fromJson(reqBody,Account.class);
        String resp = new String();
        try {
         accountService.update(account);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
            resp = "{\"status\":successfull }";

        return new ResponseEntity<String>(resp,responseHeaders,HttpStatus.CREATED);
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        String resp = new String();
        if (!id.matches("[0-9].*"))
            return new ResponseEntity<String>("bad request", responseHeaders, HttpStatus.BAD_REQUEST);
        try{
            accountService.delete(Integer.parseInt(id));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp = "{\"status\": account with id="+id+" doesn't exist}";
        }
        resp = "{\"status\": succesfully deleted !}";

        return new ResponseEntity<String>(resp, responseHeaders, HttpStatus.CREATED);
    }

}
