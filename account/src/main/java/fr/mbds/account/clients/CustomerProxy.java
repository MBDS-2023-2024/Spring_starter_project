package fr.mbds.account.clients;

import fr.mbds.account.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER")
public interface CustomerProxy {
    @GetMapping("/customer/{id}")
    @CircuitBreaker(name = "customer",fallbackMethod = "getDefaultCustomer")
    // Comment l'id a etait passer
    public Customer getCustomer(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customers",fallbackMethod = "getDefaultCustomers")
    public List<Customer> getCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception)
    {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("Not available");
        customer.setLastName("Not available");
        customer.setEmail("Not available");
        return customer;
    }
    default List<Customer> getDefaultCustomers(Exception exception)
    {
        return List.of();
    }
}