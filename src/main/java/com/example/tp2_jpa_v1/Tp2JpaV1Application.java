package com.example.tp2_jpa_v1;

import com.example.tp2_jpa_v1.entity.patient;
import com.example.tp2_jpa_v1.reporsitory.patientReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2JpaV1Application implements CommandLineRunner {
    @Autowired
    private patientReporsitory PatientReporsitory;
    public static void main(String[] args) {
        SpringApplication.run(Tp2JpaV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <100 ; i++) {
            PatientReporsitory.save(new patient(null,"said",new Date(),Math.random()>0.5?true:false, (int) (Math.random() * 100)));
        }

        Page<patient> patients = PatientReporsitory.findAll(PageRequest.of(1,5));
        System.out.printf("total de elem : " + patients.getTotalElements() + "\n");
        System.out.printf("total de page : " + patients.getTotalPages() + "\n");
        System.out.printf("total de number : " + patients.getNumber() + "\n");
        List<patient> content = patients.getContent();
        Page<patient> byMalade = PatientReporsitory.findByMalade(true, PageRequest.of(0,4));
        byMalade.forEach(p->{
            System.out.println("//////////////////////");
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getId());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());

        });
        System.out.println("++++++++++++++++++++++++++++++++");
        patient Patient=PatientReporsitory.findById(1L).orElse(null);
        if (Patient!=null){
            System.out.println(Patient.getNom());
            System.out.println(Patient.isMalade());
        }

    Patient.setScore(800);
    PatientReporsitory.save(Patient);
    PatientReporsitory.deleteById(1l);


    }
}
