package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Well;
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
public class WellDTO {

    private Long wellId;

    @NotBlank(message = "Well code cannot be blank")
    @Size(min = 2, max = 50,
            message = "Well code must be between 2 and 50 characters")
    private String wellCode;

    @NotNull(message = "Depth cannot be null")
    @Positive(message = "Depth must be greater than 0")
    private Double depth;

    @NotBlank(message = "Well type cannot be blank")
    @Size(min = 2, max = 30,
            message = "Well type must be between 2 and 30 characters")
    private String type;

    @NotBlank(message = "Well status cannot be blank")
    @Size(min = 2, max = 30,
            message = "Well status must be between 2 and 30 characters")
    private String status;

    @NotNull(message = "Oil field ID cannot be null")
    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    @NotNull(message = "Rig ID cannot be null")
    @Positive(message = "Rig ID must be positive")
    private Long rigId;

    public static WellDTO convertToDTO(Well entity) {

        return WellDTO.builder()
                .wellId(entity.getId())
                .wellCode(entity.getWellCode())
                .depth(entity.getDepth())
                .type(entity.getType())
                .status(entity.getStatus())
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .rigId(
                        entity.getRig() != null
                                ? entity.getRig().getId()
                                : null
                )
                .build();
    }

    public static List<WellDTO> convertToDTO(
            List<Well> entityList) {

        List<WellDTO> dtos = new ArrayList<>();

        for (Well entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
