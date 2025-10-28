package com.company.inventory.response;

import java.util.List;

import com.company.inventory.model.Owner;

import lombok.Data;


@Data
public class OwnerResponse {
	
	private List<Owner> owner;

}
