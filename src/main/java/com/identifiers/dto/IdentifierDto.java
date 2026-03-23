package com.identifiers.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentifierDto{
	String diio;
	String name;
	@JsonFormat(pattern="dd-MM-yyyy")
	LocalDateTime datePlacement;
	String state;
	
	public IdentifierDto(String diio, String name, LocalDateTime datePlacement, String state) {
		super();
		this.diio = diio;
		this.name = name;
		this.datePlacement = datePlacement;
		this.state = state;
	}

}
