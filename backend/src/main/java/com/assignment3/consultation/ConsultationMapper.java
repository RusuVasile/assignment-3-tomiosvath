package com.assignment3.consultation;

import com.assignment3.consultation.model.Consultation;
import com.assignment3.consultation.model.dto.ConsultationRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings({
            @Mapping(target="patientName", expression = "java(consultation.getPatient().getName())"),
            @Mapping(target="doctorName", expression = "java(consultation.getDoctor().getUsername())"),
            @Mapping(target="date", source = "consultation.date", dateFormat = "yyyy-MM-dd HH")
    })
    ConsultationRegisterDTO toDTO(Consultation consultation);

    Consultation fromDTO(ConsultationRegisterDTO consultationRegisterDTO);

}
