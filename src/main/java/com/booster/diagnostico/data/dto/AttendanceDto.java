package com.booster.diagnostico.data.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
	private Long id_attendance;
    private Long user;
    private Long session;
}
