package fr.mbds.customer.controllers;

import fr.mbds.customer.dtos.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import fr.mbds.customer.services.ICustomerService;

import java.util.List;

@RestController
public class CustomerController {
    ICustomerService customerService;
    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers()
    {
        return customerService.findAll();
    }
    @GetMapping("customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id)
    {

        return customerService.findById(id);
    }
}