package com.sagapattern.accountservice.controller;

import com.sagapattern.accountservice.entity.Account;
import com.sagapattern.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping
    @Operation(summary = "Add a new Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Account")})
    private ResponseEntity<String> addNewAccount(@RequestBody Account account){
        return accountService.addNewAccount(account);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Accounts Added")})
    private ResponseEntity<?> getAllAccounts(){
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Account details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Account with specified Id found")})
    private ResponseEntity<?> getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Account details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Account details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Account with specified Id found")})
    private ResponseEntity<String> updateAccount(@RequestBody Account account,@PathVariable Long id){
        return accountService.updateAccount(account,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Account details updated successfully")})
    private ResponseEntity<String> deleteAccountById(@PathVariable Long id){
        return accountService.deleteAccountById(id);
    }
}
