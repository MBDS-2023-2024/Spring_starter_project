package fr.mbds.customer.services;

import fr.mbds.customer.dtos.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> findAll();
    CustomerDTO findById(Long id);
}
