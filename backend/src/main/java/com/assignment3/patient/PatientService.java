package com.assignment3.patient;

import com.assignment3.patient.model.dto.PatientDTO;
import com.assignment3.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> findAll(){
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public Patient findById(Long id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No book with id: " + id));
    }

    public Patient findByName(String name){
        return patientRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    public PatientDTO create(PatientDTO patient){
        return patientMapper.toDto(patientRepository.save(patientMapper.fromDto(patient)));
    }

    public void delete(Long id){
        Patient patient = findById(id);
        patientRepository.delete(patient);
    }

    public PatientDTO edit(PatientDTO patient){
        Patient oldPatient = findById(patient.getId());
        oldPatient.setName(patient.getName());
        oldPatient.setIdCardNb(patient.getIdCardNb());
        oldPatient.setPersonalCode(patient.getPersonalCode());
        oldPatient.setBirthDate(patient.getBirthDate());
        oldPatient.setAddress(patient.getAddress());

        return patientMapper.toDto(
                patientRepository.save(oldPatient)
        );
    }

}
