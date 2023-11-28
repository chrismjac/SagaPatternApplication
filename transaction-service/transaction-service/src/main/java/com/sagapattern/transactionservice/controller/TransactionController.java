package com.sagapattern.transactionservice.controller;

import com.sagapattern.transactionservice.entity.Transaction;
import com.sagapattern.transactionservice.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Add a new Transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Transaction")})
    private ResponseEntity<String> addNewTransaction(@RequestBody Transaction transaction){
        return transactionService.addNewTransaction(transaction);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Transactions Added")})
    private ResponseEntity<?> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Transaction by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Transaction details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Transaction with specified Id found")})
    private ResponseEntity<?> getTransactionById(@PathVariable Long id){
        return transactionService.getTransactionById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Transaction details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Transaction details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Transaction with specified Id found")})
    private ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction,@PathVariable Long id){
        return transactionService.updateTransaction(transaction,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Transaction")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Transaction details updated successfully")})
    private ResponseEntity<String> deleteTransactionById(@PathVariable Long id){
        return transactionService.deleteTransactionById(id);
    }
}
