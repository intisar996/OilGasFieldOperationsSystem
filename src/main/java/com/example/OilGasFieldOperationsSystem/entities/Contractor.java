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
public class Contractor extends BaseClass {

    private String name;
    private String email;
    private String phoneNumber;
    private String country;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rig> rigs;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Personnel> personnel;
}
