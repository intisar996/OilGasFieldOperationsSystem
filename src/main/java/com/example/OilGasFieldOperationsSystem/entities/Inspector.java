package com.example.OilGasFieldOperationsSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Inspector extends BaseClass {

    private String name;
    private String licenseNumber;
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
