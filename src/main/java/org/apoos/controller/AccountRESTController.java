package org.apoos.controller;

import com.google.gson.Gson;
import org.apoos.model.Account;
import org.apoos.service.AccountService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/accountAll")
    public ModelAndView all(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<Account> all;
        all = accountService.findAll();
        System.out.println(all);
        String resp = gson.toJson(all);
        ModelAndView model = new ModelAndView("accounts");
        model.addObject("resp",resp);
        return model;
    }

    @GetMapping("/accountById")
    public ModelAndView getById(@RequestParam String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (!id.matches("[0-9].*")) return new ModelAndView("accounts").addObject("resp","BAD REQUEST");
        Account account ;
        account = accountService.findById(Integer.parseInt(id)) ;
        String resp = gson.toJson(account);
        ModelAndView model = new ModelAndView("accounts");
        model.addObject("resp",resp);
        return model;
    }

    @PostMapping("/account")
    public ModelAndView createAccount(@RequestParam("objDesc") String reqBody){
        System.out.println(reqBody);
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
            return new ModelAndView("accounts").addObject("resp",resp);
        }
            resp = "{success}";

        return new ModelAndView("accounts").addObject("resp",resp);
    }

    @PostMapping("/account/credit")
    public ModelAndView creditAccount(@RequestParam("amount") Integer amount, @RequestParam("id") Integer id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        Account account;
        String resp = new String();
        try {
            accountService.credit(id,amount);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        resp = "{\"status\":successfull }";

        return new ModelAndView("accounts").addObject("resp",resp);
    }

    @PostMapping("/account/debit")
    public ModelAndView debit(@RequestParam("amount") Integer amount, @RequestParam("id") Integer id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        String resp = new String();
        try {
            accountService.debit(id,amount);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        resp = "{\"status\":successfull }";

        return new ModelAndView("accounts").addObject("resp",resp);
    }

    @PostMapping("/accountEdit")
    public ModelAndView editAccount(@RequestParam("objDesc") String reqBody){
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

        return new ModelAndView("accounts").addObject("resp",resp);
    }

    @GetMapping("/account/delete")
    public ModelAndView deleteAccount(@RequestParam("id") String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        String resp = new String();
        if (!id.matches("[0-9].*"))
            return new ModelAndView("accounts").addObject("resp","bad request");
        try{
            accountService.delete(Integer.parseInt(id));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp = "{\"status\": account with id="+id+" doesn't exist}";
        }
        resp = "{\"status\": succesfully deleted !}";

        return new ModelAndView("accounts").addObject("resp",resp);
    }
}
