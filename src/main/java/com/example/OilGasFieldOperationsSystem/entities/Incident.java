package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Incident extends BaseClass {

    private Date incidentDate;
    private String severity;
    private String description;
    private String status;

    @ManyToOne
    private OilField oilField;

    @ManyToOne
    private Personnel reportedBy;
}
