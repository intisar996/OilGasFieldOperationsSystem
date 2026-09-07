package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Personnel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PersonnelDTO {

    @Positive(message = "Personnel ID must be positive")
    private Long personnelId;

    @NotBlank(message = "Personnel name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Personnel name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Role cannot be blank")
    @Size(min = 2, max = 50,
            message = "Role must be between 2 and 50 characters")
    private String role;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^[0-9+\\- ]{7,20}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    @NotBlank(message = "Certification cannot be blank")
    @Size(min = 2, max = 100,
            message = "Certification must be between 2 and 100 characters")
    private String certification;

    @NotNull(message = "Contractor ID cannot be null")
    @Positive(message = "Contractor ID must be positive")
    private Long contractorId;

    @NotNull(message = "Oil field ID cannot be null")
    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    public static PersonnelDTO convertToDTO(Personnel entity) {

        return PersonnelDTO.builder()
                .personnelId(entity.getId())
                .name(entity.getName())
                .role(entity.getRole())
                .phoneNumber(entity.getPhoneNumber())
                .certification(entity.getCertification())
                .contractorId(
                        entity.getContractor() != null
                                ? entity.getContractor().getId()
                                : null
                )
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .build();
    }

    public static List<PersonnelDTO> convertToDTO(
            List<Personnel> entityList) {

        List<PersonnelDTO> dtos = new ArrayList<>();

        for (Personnel entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
