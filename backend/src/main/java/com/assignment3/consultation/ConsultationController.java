package com.assignment3.consultation;

import com.assignment3.consultation.model.dto.ConsultationRegisterDTO;
import com.assignment3.patient.PatientService;
import com.assignment3.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.assignment3.UrlMapping.CONSULTATION;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

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
