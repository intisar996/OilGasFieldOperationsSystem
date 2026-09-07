package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.ProductionReading;
import jakarta.validation.constraints.Min;
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
public class ProductionReadingDTO {

    private Long productionReadingId;

    private Date readingDate;

    @Min(value = 0,message = "oil volume can not be negative")
    private Double oilVolume;

    @Min(value = 0,message = "oil volume can not be negative")
    private Double gasVolume;
    @Min(value = 0,message = "oil volume can not be negative")
    private Double waterVolume;

    private Long wellId;

    public static ProductionReadingDTO convertToDTO(
            ProductionReading entity) {

        return ProductionReadingDTO.builder()
                .productionReadingId(entity.getId())
                .readingDate(entity.getReadingDate())
                .oilVolume(entity.getOilVolume())
                .gasVolume(entity.getGasVolume())
                .waterVolume(entity.getWaterVolume())
                .wellId(
                        entity.getWell() != null
                                ? entity.getWell().getId()
                                : null
                )
                .build();
    }

    public static List<ProductionReadingDTO> convertToDTO(
            List<ProductionReading> entityList) {

        List<ProductionReadingDTO> dtos = new ArrayList<>();

        for (ProductionReading entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
