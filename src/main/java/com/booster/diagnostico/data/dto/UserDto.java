package com.booster.diagnostico.data.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class UserDto implements Serializable{

	private Long id_user;
	private String name;
	private String phone;
	
	
}
