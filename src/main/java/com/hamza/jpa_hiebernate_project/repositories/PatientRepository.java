package com.hamza.jpa_hiebernate_project.repositories;

import com.hamza.jpa_hiebernate_project.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // long as sencod parameter because the id of patient is long


    // findByMalade= select from patient where malade=m
    public List<Patient> findByMalade(boolean m);

    public Page<Patient> findByMalade(boolean m, Pageable pageable);

    // la liste des personne qui ont score >45

    public List<Patient> findByScoreGreaterThan(int score);

    // La liste des personne qui ont score >100 and malade = false

    public List<Patient> findByMaladeAndScoreGreaterThan(boolean etat, int score);

    // On peut minimiser cette methode using

    public List<Patient> findByMaladeIsTrueAndScoreGreaterThan(int score);

    // when le nom = hamza
    public List<Patient> findByNomIsLike(String nom);
// Condition sur date de naissance

    List<Patient> findByDateNaissanceBetween(Date d1, Date d2);

    // mais on peut faire mieux on utilisant annotation query

    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z ")
    List<Patient> chercherPatientCondition(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String name);


}
