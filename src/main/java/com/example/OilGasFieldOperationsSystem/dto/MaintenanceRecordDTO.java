package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.MaintenanceRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecordDTO {

    @Positive(message = "Maintenance record ID must be positive")
    private Long maintenanceRecordId;

    @NotNull(message = "Maintenance date cannot be null")
    private Date maintenanceDate;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, max = 500,
            message = "Description must be between 5 and 500 characters")
    private String description;

    @NotNull(message = "Cost cannot be null")
    @Positive(message = "Cost must be greater than 0")
    private Double cost;

    @NotNull(message = "Equipment ID cannot be null")
    @Positive(message = "Equipment ID must be positive")
    private Long equipmentId;

    @NotNull(message = "Technician ID cannot be null")
    @Positive(message = "Technician ID must be positive")
    private Long technicianId;

    public static MaintenanceRecordDTO convertToDTO(
            MaintenanceRecord entity) {

        return MaintenanceRecordDTO.builder()
                .maintenanceRecordId(entity.getId())
                .maintenanceDate(entity.getMaintenanceDate())
                .description(entity.getDescription())
                .cost(entity.getCost())
                .equipmentId(
                        entity.getEquipment() != null
                                ? entity.getEquipment().getId()
                                : null
                )
                .technicianId(
                        entity.getTechnician() != null
                                ? entity.getTechnician().getId()
                                : null
                )
                .build();
    }

    public static List<MaintenanceRecordDTO> convertToDTO(
            List<MaintenanceRecord> entityList) {

        List<MaintenanceRecordDTO> dtos = new ArrayList<>();

        for (MaintenanceRecord entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
