package com.lab4.demo.consultation;

import com.lab4.demo.consultation.model.dto.ConsultationDTO;
import com.lab4.demo.consultation.model.dto.ConsultationRegisterDTO;
import com.lab4.demo.patient.PatientService;
import com.lab4.demo.patient.model.Patient;
import com.lab4.demo.user.UserService;
import com.lab4.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.lab4.demo.UrlMapping.CONSULTATION;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    private final PatientService patientService;

    private final UserService userService;

    @GetMapping
    public List<ConsultationRegisterDTO> allConsultations(){
        return consultationService.findAll();
    }

    @PostMapping
    public void create(@RequestBody ConsultationRegisterDTO consultation){
        consultationService.create(consultation);
    }

    @PatchMapping
    public void edit(@RequestBody ConsultationRegisterDTO consultation){
        consultationService.edit(consultation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        consultationService.delete(id);
    }
}
