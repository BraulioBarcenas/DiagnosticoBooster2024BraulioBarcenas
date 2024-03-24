package com.booster.diagnostico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booster.diagnostico.data.User;
import com.booster.diagnostico.data.dto.RespuestaGenerica;
import com.booster.diagnostico.data.dto.UserDto;
import com.booster.diagnostico.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public RespuestaGenerica getUsuarios() {
		List<UserDto> userList = new ArrayList<>();
		RespuestaGenerica respuesta = new RespuestaGenerica();
		
		for (User user: userRepository.findAll()) {
		UserDto userDto = new UserDto();
		userDto.setId_user(user.getId_user());
		userDto.setName(user.getName());
		userDto.setPhone(user.getName());
		userList.add(userDto);
	}

	respuesta.setExito(true);
	respuesta.getDatos().add(userList);
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
