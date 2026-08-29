package com.sunrisedental.dao;

import java.sql.Connection;
import java.util.List;

import com.sunrisedental.dealer.AppointmentDealer;
import com.sunrisedental.dto.AppointmentDto;

public interface AppointmentDao {
	public boolean isAppointmentExists(AppointmentDealer dealer);
	public boolean isAppointmentNoExists(String appointmentNo);
	public void saveAppointment(AppointmentDealer dealer, int userId);
    List<AppointmentDto> getAppointments();
    boolean finalizeAppointment(int appointmentId, Connection connection);
    AppointmentDto findById(int appointmentId, Connection connection);
    
}
