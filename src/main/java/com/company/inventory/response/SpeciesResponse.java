package com.company.inventory.response;

import java.util.List;

import com.company.inventory.model.Species;

import lombok.Data;

@Data
public class SpeciesResponse {
	
	private List<Species> species;

}
