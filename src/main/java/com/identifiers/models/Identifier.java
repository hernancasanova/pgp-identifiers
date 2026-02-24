package com.identifiers.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="IDENTIFIERS", schema="HERNAN")
public class Identifier implements Serializable{
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 2034674840461817925L;


	@Id
	/*@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_DOCUMENT")
	@SequenceGenerator(schema = "HERNAN", name = "SEQUENCE_DOCUMENT",
    sequenceName = "SEQUENCE_DOCUMENT" , allocationSize=1)*/  
	@Column(name="DIIO")
	public String diio;
	
	
	@Column(name="BOVINE_ID")
	//@ManyToOne
    //@JoinColumn(name="BOVINE_ID", nullable=false)
	//public Bovine bovine;
	public Long bovine;
	
	
	@Column(name="DATE_PLACEMENT")
	public LocalDateTime datePlacement;

	public Long getBovine() {
		return bovine;
	}


	public void setBovine(Long bovine) {
		this.bovine = bovine;
	}


	@Column(name="STATE")
	public String state;
	
	
	public String getDiio() {
		return diio;
	}
	
	
	public void setDiio(String diio) {
		this.diio = diio;
	}
	
	
	public LocalDateTime getDatePlacement() {
		return datePlacement;
	}


	public void setDatePlacement(LocalDateTime datePlacement) {
		this.datePlacement = datePlacement;
	}


	public String getState() {
		return state;
	}
	
	
	public void setState(String state) {
		this.state = state;
	}
}
