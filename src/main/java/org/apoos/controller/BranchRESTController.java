package org.apoos.controller;

import com.google.gson.Gson;
import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.apoos.service.AccountService;
import org.apoos.service.BranchService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class BranchRESTController {
    private BranchService branchService;
    private Gson gson;
    public BranchRESTController(){

    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public void setBranchService(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/branch")
    public ResponseEntity<String> allb(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<Branch> all = branchService.findAll();
        System.out.println(all);
        String resp = gson.toJson(all);
        return new ResponseEntity<String>(resp,responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<String> getByIdb(@PathVariable String id){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (!id.matches("[0-9].*")) return new ResponseEntity<String>("bad request",responseHeaders,HttpStatus.BAD_REQUEST);
        Branch branch;
        branch = branchService.findById(Integer.parseInt(id)) ;
        System.out.println(branch);
        String resp = gson.toJson(branch);
        return new ResponseEntity<String>(resp,responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/branch")
    public ResponseEntity<String> createBranchb(@RequestBody String reqBody){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        Branch branch;
        branch = gson.fromJson(reqBody,Branch.class);
        String resp = new String();
        try {
            branchService.persist(branch);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp = "{status: account with same id already exists}";
        }
        resp = "{success}";

        return new ResponseEntity<String>(resp,responseHeaders,HttpStatus.CREATED);
    }

    @PutMapping("/branch")
    public ResponseEntity<String> editBranchb(@RequestBody String reqBody){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        Branch branch;
        branch = gson.fromJson(reqBody,Branch.class);
        String resp = new String();
        try {
            branchService.update(branch);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        resp = "{\"status\":successfull }";

        return new ResponseEntity<String>(resp,responseHeaders,HttpStatus.CREATED);
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<String> deleteBranchb(@PathVariable String id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        String resp = new String();
        if (!id.matches("[0-9].*"))
            return new ResponseEntity<String>("bad request", responseHeaders, HttpStatus.BAD_REQUEST);
        try{
            branchService.delete(Integer.parseInt(id));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            resp = "{\"status\": account with id="+id+" doesn't exist}";
        }
        resp = "{\"status\": succesfully deleted !}";

        return new ResponseEntity<String>(resp, responseHeaders, HttpStatus.CREATED);
    }
}
