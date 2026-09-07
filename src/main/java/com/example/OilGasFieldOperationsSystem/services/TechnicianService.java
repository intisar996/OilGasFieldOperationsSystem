package com.example.OilGasFieldOperationsSystem.services;

import com.example.OilGasFieldOperationsSystem.entities.Technician;
import com.example.OilGasFieldOperationsSystem.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    TechnicianRepository technicianRepository;

    @Autowired
    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public Long addTechnician(String name, String specialization,
                              String phoneNumber) {

        Technician technician = new Technician();

        technician.setIsActive(true);
        technician.setCreatedDate(new Date());
        technician.setName(name);
        technician.setSpecialization(specialization);
        technician.setPhoneNumber(phoneNumber);

        Technician saveTechnician = technicianRepository.save(technician);

        return saveTechnician.getId();
    }

    public List<Technician> getAllTechnician() {
        return technicianRepository.getAllTechnician();
    }

    public Technician getById(Long id) {

        Optional<Technician> technician =
                technicianRepository.findById(id);

        if (technician.isPresent() && technician.get().getIsActive()) {
            return technician.get();
        }

        return new Technician();
    }

    public Technician updateTechnician(Long id, String name,
                                       String specialization,
                                       String phoneNumber) throws Exception {

        Technician technicianToUpdate =
                technicianRepository.getById(id);

        if (technicianToUpdate == null) {
            throw new Exception("Technician is not found by the id");
        }

        technicianToUpdate.setUpdateDate(new Date());
        technicianToUpdate.setName(name);
        technicianToUpdate.setSpecialization(specialization);
        technicianToUpdate.setPhoneNumber(phoneNumber);

        return technicianRepository.save(technicianToUpdate);
    }

    public Boolean deleteTechnician(Long id) throws Exception {

        Technician technicianToUpdate =
                technicianRepository.getById(id);

        if (technicianToUpdate == null) {
            throw new Exception("Technician is not found by the id");
        }

        technicianToUpdate.setUpdateDate(new Date());
        technicianToUpdate.setIsActive(false);

        technicianRepository.save(technicianToUpdate);

        return true;
    }
}
