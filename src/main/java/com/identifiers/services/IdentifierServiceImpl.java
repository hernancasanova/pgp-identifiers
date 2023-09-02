package com.identifiers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.identifiers.dao.IdentifierDao;
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
	
	/*@Transactional
	@Override
	public void register(String diio,String date_placement,Long bovine) {
		List <Identifier> identifiersList = new ArrayList<Identifier>();
		String pattern = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime dateTime = LocalDateTime.parse(date_placement, formatter);
		//Bovine bov=bovineDao.findById(bovine).orElse(null);
		//identifierDao.desactivateDiios(bov.getId());
		Identifier identifier=new Identifier();
		identifier.setDate_placement(dateTime);
		identifier.setDiio(diio);
		identifier.setState("activo");
		//identifierDao.save(identifier)
		identifier.setBovine(bov);
		identifierDao.save(identifier);
		identifiersList.add(identifier);
		//bov.setIdentifiers(identifiersList);
		
	}*/
}