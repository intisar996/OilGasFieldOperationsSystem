package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Positive(message = "Customer ID must be positive")
    private Long customerId;

    @NotBlank(message = "Customer name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Customer name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^[0-9+\\- ]{7,20}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 50,
            message = "Country must be between 2 and 50 characters")
    private String country;

    public static CustomerDTO convertToDTO(Customer entity) {

        return CustomerDTO.builder()
                .customerId(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .country(entity.getCountry())
                .build();
    }

    public static List<CustomerDTO> convertToDTO(
            List<Customer> entityList) {

        List<CustomerDTO> dtos = new ArrayList<>();

        for (Customer entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
