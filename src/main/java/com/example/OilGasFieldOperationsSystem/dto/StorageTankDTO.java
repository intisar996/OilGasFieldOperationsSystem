package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.StorageTank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class StorageTankDTO {

    private Long storageTankId;

    @NotBlank(message = "Tank code cannot be blank")
    @Size(min = 2, max = 50,
            message = "Tank code must be between 2 and 50 characters")
    private String tankCode;

    @NotNull(message = "Capacity cannot be null")
    @Positive(message = "Capacity must be greater than 0")
    private Double capacity;

    @NotNull(message = "Current level cannot be null")
    @PositiveOrZero(message = "Current level cannot be negative")
    private Double currentLevel;

    @NotBlank(message = "Product cannot be blank")
    @Size(min = 2, max = 20,
            message = "Product must be between 2 and 20 characters")
    private String product;

    @NotNull(message = "Oil field ID cannot be null")
    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    public static StorageTankDTO convertToDTO(StorageTank entity) {

        return StorageTankDTO.builder()
                .storageTankId(entity.getId())
                .tankCode(entity.getTankCode())
                .capacity(entity.getCapacity())
                .currentLevel(entity.getCurrentLevel())
                .product(entity.getProduct())
                .oilFieldId(
                        entity.getOilField() != null
                                ? entity.getOilField().getId()
                                : null
                )
                .build();
    }

    public static List<StorageTankDTO> convertToDTO(
            List<StorageTank> entityList) {

        List<StorageTankDTO> dtos = new ArrayList<>();

        for (StorageTank entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
