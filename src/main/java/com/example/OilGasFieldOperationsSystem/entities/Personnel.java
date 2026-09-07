package com.example.OilGasFieldOperationsSystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Personnel extends BaseClass {

    private String name;
    private String role;
    private String phoneNumber;
    private String certification;

    @ManyToOne
    private Contractor contractor;

    @ManyToOne
    private OilField oilField;

    @OneToMany(mappedBy = "reportedBy", cascade = CascadeType.ALL)
    private java.util.List<Incident> incidents;
}
