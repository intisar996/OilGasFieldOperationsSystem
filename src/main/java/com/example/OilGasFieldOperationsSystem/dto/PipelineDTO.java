package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Pipeline;
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
public class PipelineDTO {

    @Positive(message = "Pipeline ID must be positive")
    private Long pipelineId;

    @NotBlank(message = "Pipeline code cannot be blank")
    @Size(min = 2, max = 50,
            message = "Pipeline code must be between 2 and 50 characters")
    private String pipelineCode;

    @NotNull(message = "Length cannot be null")
    @Positive(message = "Length must be greater than 0")
    private Double lengthKm;

    @NotNull(message = "Diameter cannot be null")
    @Positive(message = "Diameter must be greater than 0")
    private Double diameter;

    @NotBlank(message = "Pipeline status cannot be blank")
    @Size(min = 2, max = 30,
            message = "Pipeline status must be between 2 and 30 characters")
    private String status;

    @NotNull(message = "Oil field ID cannot be null")
    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    public static PipelineDTO convertToDTO(Pipeline entity) {

        return PipelineDTO.builder()
                .pipelineId(entity.getId())
                .pipelineCode(entity.getPipelineCode())
                .lengthKm(entity.getLengthKm())
                .diameter(entity.getDiameter())
                .status(entity.getStatus())
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .build();
    }

    public static List<PipelineDTO> convertToDTO(
            List<Pipeline> entityList) {

        List<PipelineDTO> dtos = new ArrayList<>();

        for (Pipeline entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
