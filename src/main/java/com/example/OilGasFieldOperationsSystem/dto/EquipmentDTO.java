package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Equipment;
import jakarta.validation.constraints.NotBlank;
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
public class EquipmentDTO {

    @Positive(message = "Equipment ID must be positive")
    private Long equipmentId;

    @NotBlank(message = "Equipment name cannot be blank")
    @Size(min = 2, max = 50,
            message = "Equipment name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Serial number cannot be blank")
    @Size(min = 2, max = 50,
            message = "Serial number must be between 2 and 50 characters")
    private String serialNumber;

    @NotBlank(message = "Equipment type cannot be blank")
    @Size(min = 2, max = 30,
            message = "Equipment type must be between 2 and 30 characters")
    private String type;

    @NotBlank(message = "Equipment status cannot be blank")
    @Size(min = 2, max = 30,
            message = "Equipment status must be between 2 and 30 characters")
    private String status;

    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    public static EquipmentDTO convertToDTO(Equipment entity) {

        return EquipmentDTO.builder()
                .equipmentId(entity.getId())
                .name(entity.getName())
                .serialNumber(entity.getSerialNumber())
                .type(entity.getType())
                .status(entity.getStatus())
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .build();
    }

    public static List<EquipmentDTO> convertToDTO(
            List<Equipment> entityList) {

        List<EquipmentDTO> dtos = new ArrayList<>();

        for (Equipment entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
