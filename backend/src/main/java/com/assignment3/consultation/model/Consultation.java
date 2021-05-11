package com.assignment3.consultation.model;

import com.assignment3.patient.model.Patient;
import com.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Column(length = 512, nullable = false)
    private String author;

    @Column(length = 512)
    private String details;
}
