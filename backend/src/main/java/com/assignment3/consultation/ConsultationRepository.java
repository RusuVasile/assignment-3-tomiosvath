package com.assignment3.consultation;

import com.assignment3.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    Boolean existsByDoctor_IdAndDate(Long doctor_id, LocalDateTime date);
}
