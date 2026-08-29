package com.sunrisedental.dao;

import java.util.List;

import com.sunrisedental.dto.PatientDto;

public interface PatientDao {
	 public List<PatientDto> searchPatient(String searchText);
	 public int createPatient(String name,String contactNo,String address);
}
