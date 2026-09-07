package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Technician;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class TechnicianDTO {

    private Long technicianId;

    @NotBlank(message = "Technician name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Technician name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Specialization cannot be blank")
    @Size(min = 2, max = 100,
            message = "Specialization must be between 2 and 100 characters")
    private String specialization;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(
            regexp = "^[0-9+\\- ]{7,20}$",
            message = "Invalid phone number"
    )
    private String phoneNumber;

    public static TechnicianDTO convertToDTO(Technician entity) {

        return TechnicianDTO.builder()
                .technicianId(entity.getId())
                .name(entity.getName())
                .specialization(entity.getSpecialization())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    public static List<TechnicianDTO> convertToDTO(
            List<Technician> entityList) {

        List<TechnicianDTO> dtos = new ArrayList<>();

        for (Technician entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
