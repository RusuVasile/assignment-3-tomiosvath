package com.assignment3.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PatientArrived {
    private String patientName;
    private String doctorName;
    private String date;
}
