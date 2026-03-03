package com.identifiers.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.identifiers.dto.ApiResponse;
import com.identifiers.dto.IdentifierDto;
import com.identifiers.models.Identifier;



public interface IIdentifierService {
	public List<Identifier> findAll();
	public List<Identifier> findByBovine(Long bovine_id);
	public List<Identifier> findByDiio(String diio);
	public List<IdentifierDto> identifiersIncludeBovines();
	boolean deactivate(String diio);
	public ResponseEntity<ApiResponse<String>> register(String diio,String date_placement, Long bovine);
}
