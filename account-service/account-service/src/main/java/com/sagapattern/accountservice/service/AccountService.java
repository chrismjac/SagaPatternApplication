package com.sagapattern.accountservice.service;

import com.sagapattern.accountservice.entity.Account;
import com.sagapattern.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    public ResponseEntity<String> addNewAccount(Account account) {
        try {
            accountRepository.save(account);
            return new ResponseEntity<>("Account Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new account" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        if (!allAccounts.isEmpty()) {
            return ResponseEntity.ok(allAccounts);
        }
        return new ResponseEntity<>("No Accounts Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getAccountById(Long id) {
        Optional<Account> accountDetails = accountRepository.findById(id);
        if (accountDetails.isPresent()) {
            return ResponseEntity.ok(accountDetails.get());
        }
        return new ResponseEntity<>("No account details with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateAccount(Account account, Long id) {
        ResponseEntity<?> accountDetails = getAccountById(id);
        if (accountDetails.getStatusCode().is2xxSuccessful()) {
            try {
                accountRepository.save(account);
                return new ResponseEntity<>("Account Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating account" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No account with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteAccountById(Long id) {
        accountRepository.deleteById(id);
        return new ResponseEntity<>("Account Details deleted successfully", HttpStatus.OK);
    }
}
