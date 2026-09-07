package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Rig;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class RigDTO {

    private Long rigId;

    @NotBlank(message = "Rig name cannot be blank")
    @Size(min = 2, max = 50,
            message = "Rig name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Rig model cannot be blank")
    @Size(min = 2, max = 50,
            message = "Rig model must be between 2 and 50 characters")
    private String model;

    @NotNull(message = "Rig capacity cannot be null")
    @Positive(message = "Rig capacity must be greater than 0")
    private Double capacity;

    @NotNull(message = "Contractor ID cannot be null")
    @Positive(message = "Contractor ID must be positive")
    private Long contractorId;

    public static RigDTO convertToDTO(Rig entity) {

        return RigDTO.builder()
                .rigId(entity.getId())
                .name(entity.getName())
                .model(entity.getModel())
                .capacity(entity.getCapacity())
                .contractorId(
                        entity.getContractor() != null
                                ? entity.getContractor().getId()
                                : null
                )
                .build();
    }

    public static List<RigDTO> convertToDTO(
            List<Rig> entityList) {

        List<RigDTO> dtos = new ArrayList<>();

        for (Rig entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
