package com.sagapattern.transactionservice.service;

import com.sagapattern.transactionservice.entity.Transaction;
import com.sagapattern.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    public ResponseEntity<String> addNewTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transaction);
            return new ResponseEntity<>("Transaction Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new transaction" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAll();
        if (!allTransactions.isEmpty()) {
            return ResponseEntity.ok(allTransactions);
        }
        return new ResponseEntity<>("No Transactions Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getTransactionById(Long id) {
        Optional<Transaction> transactionDetails = transactionRepository.findById(id);
        if (transactionDetails.isPresent()) {
            return ResponseEntity.ok(transactionDetails.get());
        }
        return new ResponseEntity<>("No transaction details with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateTransaction(Transaction transaction, Long id) {
        ResponseEntity<?> transactionDetails = getTransactionById(id);
        if (transactionDetails.getStatusCode().is2xxSuccessful()) {
            try {
                transactionRepository.save(transaction);
                return new ResponseEntity<>("Transaction Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating transaction" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No transaction with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
        return new ResponseEntity<>("Transaction Details deleted successfully", HttpStatus.OK);
    }
}
