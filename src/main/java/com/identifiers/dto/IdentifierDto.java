package com.identifiers.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface IdentifierDto {
	//int  getId();
	String getDiio();
	@JsonProperty(value = "bovine associated")
	String getName();
	@JsonProperty(value = "date placement")
	Date getDatePlacement();
	//Date getDateBirth();
	//String getMother();
	//int getIdMother();
	//String getType();
	//String getAge();
	//String getVerifiedSag();
	String getState();
	//String getDateSale();
}
