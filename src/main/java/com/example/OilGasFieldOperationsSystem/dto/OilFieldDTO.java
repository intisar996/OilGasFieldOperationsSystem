package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.OilField;
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
public class OilFieldDTO {

    @Positive(message = "Oil field ID must be positive")
    private Long oilFieldId;

    @NotBlank(message = "Oil field name cannot be blank")
    @Size(min = 3, max = 50,
            message = "Oil field name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    @Size(min = 2, max = 100,
            message = "Location must be between 2 and 100 characters")
    private String location;

    @NotBlank(message = "Region cannot be blank")
    @Size(min = 2, max = 50,
            message = "Region must be between 2 and 50 characters")
    private String region;

    public static OilFieldDTO convertToDTO(OilField entity) {

        return OilFieldDTO.builder()
                .oilFieldId(entity.getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .region(entity.getRegion())
                .build();
    }

    public static List<OilFieldDTO> convertToDTO(
            List<OilField> entityList) {

        List<OilFieldDTO> dtos = new ArrayList<>();

        for (OilField entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
