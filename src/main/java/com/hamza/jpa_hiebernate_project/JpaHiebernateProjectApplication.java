package com.hamza.jpa_hiebernate_project;

import com.hamza.jpa_hiebernate_project.entities.Patient;
import com.hamza.jpa_hiebernate_project.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaHiebernateProjectApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaHiebernateProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(
                new Patient(null, "hamza", new Date(), false, 12233));

        patientRepository.save(
                new Patient(null, "hamid", new Date(), false, 123));
        patientRepository.save(
                new Patient(null, "hatim", new Date(), true, 123));
        patientRepository.save(
                new Patient(null, "hakim", new Date(), true, 103));


// insert 100 patient
        for(int i=0;i<100;i++){
            patientRepository.save(
                    new Patient(null, "hamza", new Date(), false, (int)(Math.random()*100)));
        }

        // display list of patient

        List<Patient> patients = patientRepository.findAll();

//        patients.forEach(p -> {
//            System.out.println("================================");
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getScore());
//            System.out.println(p.getDateNaissance());
//            System.out.println(p.isMalade());
//        });


// Look for a patient

        Patient pat1 = patientRepository.findById(1L).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (pat1 != null){

            System.out.println("============ The patient who has this id :====================");
            System.out.println(pat1.getId());
            System.out.println(pat1.getNom());
            System.out.println(pat1.getScore());
            System.out.println(pat1.getDateNaissance());
            System.out.println(pat1.isMalade());


    }

        // delete patient

//        patientRepository.deleteById(1L);

        // update patient who has id equal  1

        pat1.setNom("HAMZA BRAIMI");
        patientRepository.save(pat1);

        // insertion de 200 enregistremets a la fois



        // how do pagination when we try to get all data
        Page<Patient> patientList= patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("==============================================>");
        System.out.println("Total pages ="+patientList.getTotalPages());
        System.out.println("Total elements ="+patientList.getTotalElements());
        System.out.println("Current pages="+patientList.getNumber());
        patientList.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });


// get list of patient where malade = true

        List<Patient> patientMalade=patientRepository.findByMalade(true);

        System.out.println("=============== La liste des personnes qui sont malade=================");
        patientMalade.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });

// get list of patient where malade = true with pagination

        Page<Patient> patientMaladePagination=patientRepository.findByMalade(true,PageRequest.of(0,5));

        patientMaladePagination.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });
//================================================= Implementation de la methode a liste des personne qui ont score >45

        List<Patient> patientScore=patientRepository.findByScoreGreaterThan(45);
        System.out.println("================================ Score >45==========================");
        patientScore.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });

//=================================================   La liste des personne qui ont score >100 and malade = false

        List<Patient> patientScoreMalade=patientRepository.findByMaladeAndScoreGreaterThan(false,100);
        System.out.println("================================ Score >100 and malade = flase ==========================");
        patientScoreMalade.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });



//=================================================  Get patients using query annotation

        List<Patient> patientQuery=patientRepository.chercherPatientCondition(new Date("10/12/2001"),new Date(),"hamza" );
        System.out.println("================================ Get patients using query annotation ==========================");
        patientQuery.forEach(p -> {
            System.out.println("================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
        });








    }
}
