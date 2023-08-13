package com.identifiers.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.identifiers.models.Identifier;

public interface IdentifierDao extends JpaRepository<Identifier,Long>{
	
	//@Modifying
	//@Query(value="UPDATE HERNAN.IDENTIFIERS i SET i.STATE='inactivo' WHERE i.STATE='activo' AND i.BOVINE_ID = ? ", nativeQuery=true)
	//void desactivateDiios(Long bovine_id);
	//@Query(value="SELECT b.ID, b.NAME AS Name, nvl(i.DIIO,'Sin arete') AS Diio, i.DATE_PLACEMENT AS datePlacement, b.DATE_BIRTH AS dateBirth, bo.NAME AS Mother , tb.NAME AS Type FROM HERNAN.BOVINES b LEFT JOIN HERNAN.IDENTIFIERS i on b.ID = i.BOVINE_ID JOIN HERNAN.BOVINES bo on b.MOTHER = bo.ID  JOIN HERNAN.TYPES_BOVINES tb on tb.ID=b.TYPE WHERE b.ID = ? AND b.DATE_SALE IS NULL AND (i.STATE='activo' or i.DIIO is NULL ) AND b.STATE = 'Vivo' AND  b.ID <> 0", nativeQuery=true)
	@Query(value="SELECT i.* FROM HERNAN.IDENTIFIERS i WHERE i.bovine_id = ?", nativeQuery=true)
	List<Identifier> findByBovine(Long bovine);
	//List<Identifier> findByBovine(Long bovine_id);
	List<Identifier> findByDiio(String diio);
}
