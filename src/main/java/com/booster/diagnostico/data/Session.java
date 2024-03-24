package com.booster.diagnostico.data;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_session")
	private Long id_session;
	
	@Column(name="name")
	private String name;
	
	@Column(name="topic")
	private String topic;
	
	@Column(name = "date")
	private Date date;
	
}
