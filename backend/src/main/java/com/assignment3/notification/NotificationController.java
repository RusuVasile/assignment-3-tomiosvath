package com.assignment3.notification;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/checkIn")
    @SendTo("/doctor/checkInNotification")
    public DoctorNotification checkIn(PatientArrived receiveMessage) throws Exception {
        return new DoctorNotification("Patient " + receiveMessage.getPatientName()
                + ", with consultation at " + receiveMessage.getDate() + "o'clock has arrived!", receiveMessage.getDoctorName());
    }
}
