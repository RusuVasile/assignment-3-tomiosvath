package com.lab4.demo.patient;

import com.lab4.demo.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    //@Query("SELECT p from Patient p where p.name = ?1")
    //Patient findByName(@Param("name") String name);

    Optional<Patient> findByName(String name);
}
