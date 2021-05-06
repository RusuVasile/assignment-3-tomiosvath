package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.Consultation;
import com.lab4.demo.consultation.model.dto.ConsultationDTO;
import com.lab4.demo.consultation.model.dto.ConsultationRegisterDTO;
import com.lab4.demo.patient.PatientService;
import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.user.UserService;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserService userService;
    private final PatientService patientService;

    public List<ConsultationRegisterDTO> findAll() {
        List<ConsultationDTO> consultations = consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        List<ConsultationRegisterDTO> consultationRegisterDTOS = new ArrayList<>();

        for (ConsultationDTO consultation : consultations){
            consultationRegisterDTOS.add(ConsultationRegisterDTO.builder()
                    .id(consultation.getId())
                    .doctorName(consultation.getDoctor().getUsername())
                    .patientName(consultation.getPatient().getName())
                    .date(consultation.getDate().format(formatter))
                    .author(consultation.getAuthor())
                    .details(consultation.getDetails())
                    .build());
        }

        return consultationRegisterDTOS;

        /*return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());*/
    }

    public Consultation findById(Long id){
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No consultation with id: " + id));
    }

    public ConsultationDTO create(ConsultationRegisterDTO consultation){
        Patient patient = patientService.findByName(consultation.getPatientName());
        User doctor = userService.findDoctorByName(consultation.getDoctorName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime formatted = LocalDateTime.parse(consultation.getDate(), formatter);

        return consultationMapper.toDto(consultationRepository.save(consultationMapper.fromDto(
            ConsultationDTO.builder()
                    .date(formatted)
                    .patient(patient)
                    .doctor(doctor)
                    .author(consultation.getAuthor())
                    .details(consultation.getDetails())
                    .build())));

        //return consultationMapper.toDto(consultationRepository.save(consultationMapper.fromDto(consultationDTO)));
    }

    public void delete(Long id){
        Consultation consultation = findById(id);
        consultationRepository.delete(consultation);
    }

    public void edit(ConsultationRegisterDTO consultationDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        LocalDateTime formatted = LocalDateTime.parse(consultationDTO.getDate(), formatter);

        Consultation consultation = findById(consultationDTO.getId());
        consultation.setDate(formatted);
        //consultation.setDoctor(userService.findDoctorByName(consultationDTO.getDoctorName()));
        //consultation.setPatient(patientService.findByName(consultationDTO.getPatientName()));
        consultation.setDetails(consultationDTO.getDetails());
        consultation.setAuthor(consultationDTO.getAuthor());

        //System.out.println(consultation.toString());

        consultationRepository.save(consultation);
    }

}
