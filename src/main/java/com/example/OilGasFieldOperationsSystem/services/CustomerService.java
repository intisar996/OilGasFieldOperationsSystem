package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Customer;
import com.example.OilGasFieldOperationsSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long addCustomer(String name, String email,
                            String phoneNumber, String country) {

        Customer customer = new Customer();

        customer.setIsActive(true);
        customer.setCreatedDate(new Date());
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setCountry(country);

        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer.getId();
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.getAllCustomer();
    }

    public Customer getById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent() && customer.get().getIsActive()) {
            return customer.get();
        }

        return new Customer();
    }

    public Customer updateCustomer(Long id, String name,
                                   String email, String phoneNumber,
                                   String country) throws Exception {

        Customer customerToUpdate = customerRepository.getById(id);

        if (customerToUpdate == null) {
            throw new Exception("Customer is not found by the id");
        }

        customerToUpdate.setUpdateDate(new Date());
        customerToUpdate.setName(name);
        customerToUpdate.setEmail(email);
        customerToUpdate.setPhoneNumber(phoneNumber);
        customerToUpdate.setCountry(country);

        return customerRepository.save(customerToUpdate);
    }

    public Boolean deleteCustomer(Long id) throws Exception {

        Customer customerToUpdate = customerRepository.getById(id);

        if (customerToUpdate == null) {
            throw new Exception("Customer is not found by the id");
        }

        customerToUpdate.setUpdateDate(new Date());
        customerToUpdate.setIsActive(false);

        customerRepository.save(customerToUpdate);

        return true;
    }
}
