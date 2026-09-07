package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Inspector;
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
public class InspectorDTO {

    @Positive(message = "Inspector ID must be positive")
    private Long inspectorId;

    @NotBlank(message = "Inspector name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Inspector name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "License number cannot be blank")
    @Size(min = 2, max = 50,
            message = "License number must be between 2 and 50 characters")
    private String licenseNumber;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^[0-9+\\- ]{7,20}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    public static InspectorDTO convertToDTO(Inspector entity) {

        return InspectorDTO.builder()
                .inspectorId(entity.getId())
                .name(entity.getName())
                .licenseNumber(entity.getLicenseNumber())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    public static List<InspectorDTO> convertToDTO(
            List<Inspector> entityList) {

        List<InspectorDTO> dtos = new ArrayList<>();

        for (Inspector entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
