package com.example.application.data.ViCon.repository;

import com.example.application.data.ViCon.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

    Patient findByName(String name);
}
