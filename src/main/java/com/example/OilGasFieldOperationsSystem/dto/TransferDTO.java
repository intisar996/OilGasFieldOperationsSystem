package com.example.OilGasFieldOperationsSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {


    private Long productionReadingId;
    private Long storageTankId;
    private String product;

}
