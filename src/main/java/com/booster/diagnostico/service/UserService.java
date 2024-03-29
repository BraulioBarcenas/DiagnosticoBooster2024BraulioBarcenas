package com.booster.diagnostico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booster.diagnostico.data.User;
import com.booster.diagnostico.data.dto.RespuestaGenerica;
import com.booster.diagnostico.data.dto.UserDto;
import com.booster.diagnostico.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.PathParam;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public RespuestaGenerica getUsuarios(String Campo, String Valor) {
		List<UserDto> userList = new ArrayList<>();
		RespuestaGenerica respuesta = new RespuestaGenerica();
		
		for (User user: userRepository.findAll()) {
		UserDto userDto = new UserDto();
		userDto.setId_user(user.getId_user());
		userDto.setName(user.getName());
		userDto.setPhone(user.getPhone());
		userList.add(userDto);
	}

	if (Campo == "name") {
		userList =userList.stream().filter(user -> user.getName().equals(Valor)).collect(Collectors.toList());
	}

	if (Campo == "phone") {
		userList =userList.stream().filter(user -> user.getPhone().equals(Valor)).collect(Collectors.toList());
	}

	respuesta.setExito(true);
	respuesta.getDatos().add(userList);
	respuesta.setMensaje("USUARIOS OBTENIDOS");

	return respuesta;
	}


	public RespuestaGenerica getUsuarioById(Long id) {
		UserDto dto = new UserDto();
		RespuestaGenerica respuesta = new RespuestaGenerica();
		User recoveredUser = userRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
		
		dto.setId_user(recoveredUser.getId_user());
		dto.setName(recoveredUser.getName());
		dto.setPhone(recoveredUser.getPhone());

		respuesta.setExito(true);
		respuesta.getDatos().add(dto);
		respuesta.setMensaje("USUARIOS OBTENIDOS");

		return respuesta;
	}
	  
	public RespuestaGenerica saveUsuario(UserDto userDto){
		RespuestaGenerica respuesta = new RespuestaGenerica();
		User user = new User();

		user.setName(userDto.getName());
		user.setPhone(userDto.getPhone());
		user = userRepository.save(user);
		userDto.setId_user(user.getId_user());

		respuesta.setExito(true);
		respuesta.setMensaje("Usuario creado");
		respuesta.getDatos().add(userDto);
		return respuesta;
	}
	
}
