package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Inspection extends BaseClass {

    private Date inspectionDate;
    private String result;
    private String notes;

    @ManyToOne
    private Well well;

    @ManyToOne
    private Pipeline pipeline;

    @ManyToOne
    private Inspector inspector;
}
