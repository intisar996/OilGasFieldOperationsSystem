package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class ProductionReading extends BaseClass {

    private Date readingDate;
    private Double oilVolume;
    private Double gasVolume;
    private Double waterVolume;

    @ManyToOne
    private Well well;
}
