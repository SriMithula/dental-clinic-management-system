package com.sunrisedental.dao;

import java.util.List;

import com.sunrisedental.dto.TreatmentDto;

public interface TreatmentDao {
	  public List<TreatmentDto> getActiveTreatments();
}
