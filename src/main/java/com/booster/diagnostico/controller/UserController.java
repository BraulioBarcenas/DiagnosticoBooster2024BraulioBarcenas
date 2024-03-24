package com.booster.diagnostico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booster.diagnostico.data.dto.RespuestaGenerica;
import com.booster.diagnostico.data.dto.UserDto;
import com.booster.diagnostico.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<RespuestaGenerica> obtenerUsuarios() {
    
    RespuestaGenerica respuesta = userService.getUsuarios();
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
  public ResponseEntity<RespuestaGenerica> guardarUsuario(@RequestBody UserDto dto){
    RespuestaGenerica respuesta = userService.saveUsuario(dto);
    HttpStatus status = null;

    if (respuesta.isExito()) {
      status = HttpStatus.OK;
      respuesta.setCodigo(status.value());
    } else {
      status = HttpStatus.BAD_REQUEST;
      respuesta.setCodigo(status.value());
    }

    return new ResponseEntity<>(respuesta,status);

  }


}