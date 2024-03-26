package com.booster.diagnostico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booster.diagnostico.data.dto.AttendanceDto;
import com.booster.diagnostico.data.dto.RespuestaGenerica;
import com.booster.diagnostico.data.dto.SessionDto;
import com.booster.diagnostico.service.SessionService;

@CrossOrigin
@Controller
@RequestMapping("/api/sessions")
public class SessionController {
    
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public ResponseEntity<RespuestaGenerica> obtenerSesiones() {

        RespuestaGenerica respuesta = sessionService.getSesiones();
        HttpStatus status = null;

        if (respuesta.isExito()) {
        status = HttpStatus.OK;
        respuesta.setCodigo(status.value());
        } else {
        status = HttpStatus.NOT_FOUND;
        respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
    }   

    @PostMapping("/save")
    public ResponseEntity<RespuestaGenerica> nuevaSesion(@RequestBody SessionDto dto){

        RespuestaGenerica respuesta = sessionService.newSesion(dto);
        HttpStatus status = null;

        if (respuesta.isExito()) {
        status = HttpStatus.OK;
        respuesta.setCodigo(status.value());
        } else {
        status = HttpStatus.NOT_FOUND;
        respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
    }

    @PostMapping("/enroll/id")
    public ResponseEntity<RespuestaGenerica> inscribirPorId(@RequestBody AttendanceDto dto){

        RespuestaGenerica respuesta = sessionService.asignarUsuarioASesionByID(dto);
        HttpStatus status = null;

        if (respuesta.isExito()) {
        status = HttpStatus.OK;
        respuesta.setCodigo(status.value());
        } else {
        status = HttpStatus.NOT_FOUND;
        respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
    }

    @GetMapping("/attendance")
    public ResponseEntity<RespuestaGenerica> obtenerListaAsistencia(Long id_session){

        RespuestaGenerica respuesta = sessionService.obtenerAsistentes(id_session);
        HttpStatus status = null;

        if (respuesta.isExito()) {
        status = HttpStatus.OK;
        respuesta.setCodigo(status.value());
        } else {
        status = HttpStatus.NOT_FOUND;
        respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
    }
}
