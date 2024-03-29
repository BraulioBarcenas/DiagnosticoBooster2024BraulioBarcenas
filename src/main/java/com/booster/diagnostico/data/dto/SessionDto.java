package com.booster.diagnostico.data.dto;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SessionDto {
    
	private Long id_session;
	private String name;
	private String topic;	
	private Date date;
}
