package com.sunrisedental.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.sunrisedental.dto.PatientBill;
import com.sunrisedental.validation.AppointmentDateTimeValidator;


public class PatientBillTest {

    @Test
    void testBillTotalIsCostPlusConsultationFee() {

        double treatmentCost = 3500.00;
        double consultationFee = 1000.00;

        PatientBill bill = new PatientBill(
            "APT-2026-0142",
            "Nimal Perera",
            "Root Canal",
            treatmentCost,
            consultationFee
        );

        double expectedTotal = treatmentCost + consultationFee;

        assertEquals(expectedTotal,
            bill.getTreatmentFee() + bill.getConsultationFee());
    }
    
    @Test
    void testPastTimeTodayIsRejected() {

        AppointmentDateTimeValidator validator =
                new AppointmentDateTimeValidator();

        String result = validator.validate(
                LocalDate.now(),
                LocalTime.now().minusHours(1)
        );

        assertNotNull(result);
    }
}