package fr.mbds.customer;

import fr.mbds.customer.entities.Customer;
import fr.mbds.customer.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer customer = Customer.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john.doa@etu.unice.fr")
                    .build();
            customerRepository.save(customer);
        };
    }

}
