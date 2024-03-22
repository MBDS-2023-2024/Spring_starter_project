package fr.mbds.account;

import fr.mbds.account.clients.CustomerProxy;
import fr.mbds.account.entities.Account;
import fr.mbds.account.enums.CurrencyType;
import fr.mbds.account.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AccountRepository accountRepository, CustomerProxy customerClient) {
        return args -> {
            // accountRepository.save(new Account("1", "John Doe", 1000, CurrencyType.EUR));
            // accountRepository.save(new Account("2", "Jane Doe", 2000, CurrencyType.USD));
            customerClient.getCustomers().forEach(customer -> {
                Account accountInstance = Account.builder()
                        .customerId(customer.getId())
                        .id(UUID.randomUUID().toString())
                        .balance(Math.random()*1000)
                        .dateCreated(LocalDate.now())
                        .currency(CurrencyType.EUR)
                        .build();
                accountRepository.save(accountInstance);
            });
        };
    }

}
