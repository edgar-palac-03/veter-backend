package com.company.inventory.response;

import java.util.List;

import com.company.inventory.model.Specialty;

import lombok.Data;

@Data
public class SpecialtyResponse {
	
	private List<Specialty> specialty;

}
