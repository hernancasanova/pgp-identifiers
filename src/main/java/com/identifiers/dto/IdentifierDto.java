package com.identifiers.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface IdentifierDto {
	//int  getId();
	@JsonProperty(value = "Diio")
	String getDiio();
	@JsonProperty(value = "Bovine associated")
	String getName();
	@JsonProperty(value = "Date placement")
	@JsonFormat(pattern="dd-MM-yyyy")
	Date getDatePlacement();
	//Date getDateBirth();
	//String getMother();
	//int getIdMother();
	//String getType();
	//String getAge();
	//String getVerifiedSag();
	@JsonProperty(value = "State")
	String getState();
	//String getDateSale();
}
