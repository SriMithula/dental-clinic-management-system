package com.sunrisedental.dao;

import java.util.List;

import com.sunrisedental.dto.DentistDto;

public interface DentistDao {
	public List<DentistDto> getActiveDentists();
}
