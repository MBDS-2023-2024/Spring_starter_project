package fr.mbds.customer.services;

import fr.mbds.customer.dtos.CustomerDTO;
import fr.mbds.customer.entities.Customer;
import fr.mbds.customer.mappers.CustomerMapper;
import fr.mbds.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null)
            return customerMapper.toDTO(customer);
        return null;
    }
}
