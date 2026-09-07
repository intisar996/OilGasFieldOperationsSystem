package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.entities.Customer;
import com.example.OilGasFieldOperationsSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Customer")
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("add")
    public Long addCustomer(@RequestBody Customer customer) {

        return customerService.addCustomer(
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCountry()
        );
    }

    @GetMapping("getAll")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("getById")
    public Customer getById(@RequestParam Long id) {
        return customerService.getById(id);
    }

    @PutMapping("update")
    public Customer updateCustomer(
            @RequestBody Customer customer) throws Exception {

        return customerService.updateCustomer(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCountry()
        );
    }

    @DeleteMapping("deleteById")
    public Boolean deleteCustomer(@RequestParam Long id) throws Exception {
        return customerService.deleteCustomer(id);
    }
}
