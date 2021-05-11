package com.assignment3;

import com.assignment3.consultation.ConsultationService;
import com.assignment3.patient.PatientService;
import com.assignment3.patient.model.dto.PatientDTO;
import com.assignment3.security.AuthService;
import com.assignment3.security.dto.SignupRequest;
import com.assignment3.user.RoleRepository;
import com.assignment3.user.UserRepository;
import com.assignment3.user.model.ERole;
import com.assignment3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientService patientService;

    private final ConsultationService consultationService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }

            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("DOCTOR"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex2@email.com")
                    .username("alex2")
                    .password("WooHoo1!")
                    .roles(Set.of("SECRETARY"))
                    .build());
            patientService.create(PatientDTO.builder()
                    .name("Pop Andrei")
                    .idCardNb("SX190190")
                    .personalCode("1981234567891")
                    .birthDate(LocalDate.now())
                    .address("Cluj")
                    .build());

            
        }
    }
}
