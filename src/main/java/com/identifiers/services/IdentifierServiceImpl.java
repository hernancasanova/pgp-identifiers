package com.identifiers.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.identifiers.dao.IdentifierDao;
import com.identifiers.dto.ApiResponse;
import com.identifiers.dto.IdentifierDto;
import com.identifiers.models.Identifier;
//import com.pgp.dao.BovineDao;
//import com.pgp.models.Bovine;

@Service
public class IdentifierServiceImpl implements IIdentifierService{
	//IdentifierDao
	//@Autowired
	//private BovineDao bovineDao; 

	@Autowired
	private IdentifierDao identifierDao; 
	
	@Transactional(readOnly=true)
	public List<Identifier> findByDiio(String diio){
		//return (List<Identifier>)identifierDao.findAll();
		return (List<Identifier>)identifierDao.findByDiio(diio);
	}
	
	@Transactional(readOnly=true)
	public List<Identifier> findByBovine(Long bovine){
		//return (List<Identifier>)identifierDao.findAll();
		return (List<Identifier>)identifierDao.findByBovine(bovine);
	}

	@Override
	public List<Identifier> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<IdentifierDto> identifiersIncludeBovines() {
		return (List<IdentifierDto>)identifierDao.identifiersIncludeBovines();
	}

	@Override
	public boolean deactivate(String diio){
		boolean result=false;
		try {
			result=identifierDao.deactivate(diio);
			return result;
		} catch (Exception e) {
			System.out.println("Error al desactivar DIIO");
			return result;
		}
	}
	
	@Transactional
	@Override
	public ResponseEntity<ApiResponse<String>> register(String diio,String date_placement,Long bov) {
		//List <Identifier> identifiersList = new ArrayList<Identifier>();
		String pattern = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime dateTime = LocalDateTime.parse(date_placement, formatter);
		//Bovine bov=bovineDao.findById(bov).orElse(null);
		if(bov!=null) {
			identifierDao.desactivateDiios(bov);
			Identifier identifier=new Identifier();
			identifier.setDatePlacement(dateTime);
			identifier.setDiio(diio);
			identifier.setState("activo");
			//identifierDao.save(identifier)
			identifier.setBovine(bov);
			Identifier identifierSaved = identifierDao.save(identifier);
			//String diioCreated =identifierSaved.getDiio();
			ApiResponse<String> response = new ApiResponse<>(
	                200,
	                "Identifier created successfully",
	                identifierSaved.getDiio()
	            );
	            
	        return ResponseEntity.ok(response);
			//identifiersList.add(identifier);
			//bov.setIdentifiers(identifiersList);
		}
		else {
			// ApiResponse<String> response = new ApiResponse<>(
	        //         400,
	        //         "Bovine id is required",
	        //         "Bovine id is required"
	        //     );
	            
			return ResponseEntity
			        .badRequest()
			        .body(ApiResponse.error(400, "Bovine id is required"));
		}
	}
}