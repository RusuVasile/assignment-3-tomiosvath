package com.assignment3.patient;

import com.assignment3.patient.model.dto.PatientDTO;
import com.assignment3.patient.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);

    Patient fromDto(PatientDTO patient);
}
