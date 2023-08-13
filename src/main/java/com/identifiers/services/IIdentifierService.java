package com.identifiers.services;

import java.util.List;

import com.identifiers.models.Identifier;



public interface IIdentifierService {
	public List<Identifier> findAll();
	public List<Identifier> findByBovine(Long bovine_id);
	public List<Identifier> findByDiio(String diio);
	//public void register(String diio,String date_placement, Long bovine);
}
