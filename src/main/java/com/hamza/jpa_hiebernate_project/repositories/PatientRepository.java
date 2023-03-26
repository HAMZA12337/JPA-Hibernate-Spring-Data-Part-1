package com.hamza.jpa_hiebernate_project.repositories;

import com.hamza.jpa_hiebernate_project.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    // long as sencod parameter because the id of patient is long












}
