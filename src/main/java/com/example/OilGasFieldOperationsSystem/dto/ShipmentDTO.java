package com.example.OilGasFieldOperationsSystem.dto;

import com.example.OilGasFieldOperationsSystem.entities.Shipment;
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
public class ShipmentDTO {

    private Long shipmentId;

    @NotNull(message = "Shipment date cannot be null")
    private Date shipmentDate;

    @NotNull(message = "Volume cannot be null")
    @Positive(message = "Volume must be greater than 0")
    private Double volume;

    @NotBlank(message = "Destination cannot be blank")
    @Size(min = 2, max = 100,
            message = "Destination must be between 2 and 100 characters")
    private String destination;

    @NotBlank(message = "Status cannot be blank")
    @Size(min = 2, max = 30,
            message = "Status must be between 2 and 30 characters")
    private String status;

    @NotNull(message = "Storage tank ID cannot be null")
    @Positive(message = "Storage tank ID must be positive")
    private Long storageTankId;

    @NotNull(message = "Customer ID cannot be null")
    @Positive(message = "Customer ID must be positive")
    private Long customerId;

    public static ShipmentDTO convertToDTO(Shipment entity) {

        return ShipmentDTO.builder()
                .shipmentId(entity.getId())
                .shipmentDate(entity.getShipmentDate())
                .volume(entity.getVolume())
                .destination(entity.getDestination())
                .status(entity.getStatus())
                .storageTankId(
                        entity.getStorageTank() != null
                                ? entity.getStorageTank().getId()
                                : null
                )
                .customerId(
                        entity.getCustomer() != null
                                ? entity.getCustomer().getId()
                                : null
                )
                .build();
    }

    public static List<ShipmentDTO> convertToDTO(
            List<Shipment> entityList) {

        List<ShipmentDTO> dtos = new ArrayList<>();

        for (Shipment entity : entityList) {
            dtos.add(convertToDTO(entity));
        }

        return dtos;
    }
}
