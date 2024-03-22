package fr.mbds.account.controllers;


import fr.mbds.account.clients.CustomerProxy;
import fr.mbds.account.entities.Account;
import fr.mbds.account.models.Customer;
import fr.mbds.account.repositories.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    AccountRepository accountRepository;
    CustomerProxy customerClient;

    public AccountController(AccountRepository accountRepository, CustomerProxy customerClient) {
        this.accountRepository = accountRepository;
        this.customerClient = customerClient;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts()
    {
        List<Account> accountList = accountRepository.findAll();
        accountList.forEach(account -> {
            account.setCustomer(customerClient.getCustomer(account.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/account/{id}")
    public Account getAccountById(@PathVariable String id)
    {
        Account accountInstance = accountRepository.findById(id).orElse(null);
        if (accountInstance == null) {
            return null;
        }
        Customer customer = customerClient.getCustomer(accountInstance.getCustomerId());
        accountInstance.setCustomer(customer);
        return accountInstance;
    }
}