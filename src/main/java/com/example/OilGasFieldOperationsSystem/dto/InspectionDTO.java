package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Inspection;
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
public class InspectionDTO {

    @Positive(message = "Inspection ID must be positive")
    private Long inspectionId;

    @NotNull(message = "Inspection date cannot be null")
    private Date inspectionDate;

    @NotBlank(message = "Result cannot be blank")
    @Size(max = 100,
            message = "Result cannot exceed 100 characters")
    private String result;

    @Size(max = 500,
            message = "Notes cannot exceed 500 characters")
    private String notes;

    @Positive(message = "Well ID must be positive")
    private Long wellId;

    @Positive(message = "Pipeline ID must be positive")
    private Long pipelineId;

    @NotNull(message = "Inspector ID cannot be null")
    @Positive(message = "Inspector ID must be positive")
    private Long inspectorId;

    public static InspectionDTO convertToDTO(Inspection entity) {

        return InspectionDTO.builder()
                .inspectionId(entity.getId())
                .inspectionDate(entity.getInspectionDate())
                .result(entity.getResult())
                .notes(entity.getNotes())
                .wellId(
                        entity.getWell() != null
                                ? entity.getWell().getId()
                                : null
                )
                .pipelineId(
                        entity.getPipeline() != null
                                ? entity.getPipeline().getId()
                                : null
                )
                .inspectorId(
                        entity.getInspector() != null
                                ? entity.getInspector().getId()
                                : null
                )
                .build();
    }

    public static List<InspectionDTO> convertToDTO(
            List<Inspection> entityList) {

        List<InspectionDTO> dtos = new ArrayList<>();

        for (Inspection entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
