package com.example.OilGasFieldOperationsSystem.controllers;

import com.example.OilGasFieldOperationsSystem.dto.CustomerDTO;
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
    public Long addCustomer(@RequestBody CustomerDTO customer) {

        return customerService.addCustomer(
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCountry()
        );
    }

    @GetMapping("getAll")
    public List<CustomerDTO> getAllCustomer() {
        return CustomerDTO.convertToDTO(customerService.getAllCustomer());
    }

    @GetMapping("getById")
    public CustomerDTO getById(@RequestParam Long id) {
        return CustomerDTO.convertToDTO(customerService.getById(id));
    }

    @PutMapping("update")
    public CustomerDTO updateCustomer(
            @RequestBody CustomerDTO customer) throws Exception {

        return CustomerDTO.convertToDTO(customerService.updateCustomer(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCountry()
        ));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteCustomer(@RequestParam Long id) throws Exception {
        return customerService.deleteCustomer(id);
    }
}
