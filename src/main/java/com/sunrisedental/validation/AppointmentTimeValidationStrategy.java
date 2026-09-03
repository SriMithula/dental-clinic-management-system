package com.sunrisedental.validation;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentTimeValidationStrategy {

    boolean isValid(LocalDate appointmentDate, LocalTime appointmentTime);

    String getInvalidMessage();
}