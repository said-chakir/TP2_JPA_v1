package com.example.tp2_jpa_v1.reporsitory;

import com.example.tp2_jpa_v1.entity.patient;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface patientReporsitory extends JpaRepository<patient,Long> {

    List<patient> findByMalade(Boolean M);
    Page<patient> findByMalade(boolean m , Pageable pageable);
    List<patient>findByMaladeAndScoreLessThan(boolean m ,int score);
    List<patient>findByMaladeIsTrueAndScoreLessThan(int score);
    List<patient>findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Data d1 , Data d2 ,String mc);
    @Query("select p from  patient p where p.nom like :x and p.score<:y")
    List<patient>cherchePatient(@Param("x") String nom ,@Param("y") int scoreMin);
}
