package com.assignment3.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DoctorNotification {
    private String message;
    private String doctorName;
}
