package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Incident;
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
public class IncidentDTO {

    @Positive(message = "Incident ID must be positive")
    private Long incidentId;

    @NotNull(message = "Incident date cannot be null")
    private Date incidentDate;

    @NotBlank(message = "Severity cannot be blank")
    private String severity;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, max = 500,
            message = "Description must be between 5 and 500 characters")
    private String description;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    @Positive(message = "Personnel ID must be positive")
    private Long personnelId;

    public static IncidentDTO convertToDTO(Incident entity) {

        return IncidentDTO.builder()
                .incidentId(entity.getId())
                .incidentDate(entity.getIncidentDate())
                .severity(entity.getSeverity())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .personnelId(
                        entity.getReportedBy() != null
                                ? entity.getReportedBy().getId()
                                : null
                )
                .build();
    }

    public static List<IncidentDTO> convertToDTO(
            List<Incident> entityList) {

        List<IncidentDTO> dtos = new ArrayList<>();

        for (Incident entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
