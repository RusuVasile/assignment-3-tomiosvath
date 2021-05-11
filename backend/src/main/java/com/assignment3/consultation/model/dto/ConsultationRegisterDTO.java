package com.assignment3.consultation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationRegisterDTO {
    private Long id;
    private String date;
    private String patientName;
    private String doctorName;
    private String author;
    private String details;
}
