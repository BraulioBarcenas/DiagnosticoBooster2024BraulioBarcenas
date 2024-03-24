package com.booster.diagnostico.data.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaGenerica {
    
	private int codigo;
	private boolean exito;
	private String mensaje;
	private List<Object> datos;
	
	public RespuestaGenerica() {
		this.datos=new ArrayList<>();
	}
}