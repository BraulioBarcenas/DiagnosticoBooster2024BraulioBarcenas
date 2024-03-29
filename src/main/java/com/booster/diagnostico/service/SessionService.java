package com.booster.diagnostico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booster.diagnostico.data.Attendance;
import com.booster.diagnostico.data.Session;
import com.booster.diagnostico.data.User;
import com.booster.diagnostico.data.dto.AttendanceDto;
import com.booster.diagnostico.data.dto.RespuestaGenerica;
import com.booster.diagnostico.data.dto.SessionDto;
import com.booster.diagnostico.repository.AttendanceRepository;
import com.booster.diagnostico.repository.SessionRepository;
import com.booster.diagnostico.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    public RespuestaGenerica getSesiones(){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        List<SessionDto> sessionList = new ArrayList<>();

        for (Session session : sessionRepository.findAll()) {
            
            SessionDto dto = new SessionDto();
            dto.setDate(session.getDate());
            dto.setName(session.getName());
            dto.setTopic(session.getTopic());
            dto.setId_session(session.getId_session());

            sessionList.add(dto);
        }

        
        respuesta.setExito(true);
        respuesta.getDatos().add(sessionList);
        respuesta.setMensaje("Conferencias obtenidas");

        return respuesta;
    }


    public RespuestaGenerica newSesion(SessionDto dto){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Session session = new Session();
        
        session.setName(dto.getName());
        session.setTopic(dto.getTopic());
        session.setDate(dto.getDate());
        sessionRepository.save(session);
        dto.setId_session(session.getId_session());

        respuesta.setExito(true);
        respuesta.getDatos().add(dto);
        respuesta.setMensaje("Conferencia creada");

        return respuesta;
    }

    public RespuestaGenerica deleteSession(Long id){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        SessionDto sessionDto = new SessionDto();
        Session session = sessionRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
        
        List<Attendance> attendances = attendanceRepository.findBySession(session);
        
        for (Attendance attendance : attendances) {
            attendanceRepository.delete(attendance);
        }

        sessionDto.setId_session(session.getId_session());
        sessionDto.setDate(session.getDate());
        sessionDto.setName(session.getName());
        sessionDto.setTopic(session.getTopic());

        sessionRepository.delete(session);

        respuesta.setExito(true);
        respuesta.getDatos().add(sessionDto);
        respuesta.setMensaje("Sesion eliminada");

        return respuesta;
    }


    public RespuestaGenerica updateSession(SessionDto dto){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Session session = sessionRepository.findById(dto.getId_session()).orElseThrow(()-> new EntityNotFoundException()); 

        if (dto.getName() != null) {
            session.setName(dto.getName());
        }

        if (dto.getTopic() != null) {
            session.setTopic(dto.getTopic());
        }
        
        if (dto.getDate() != null) {
            session.setDate(dto.getDate());
        }

        sessionRepository.save(session);
        
        respuesta.setExito(true);
        respuesta.getDatos().add(dto);
        respuesta.setMensaje("Conferencia actualizada");

        return respuesta;
    }


    public RespuestaGenerica asignarUsuarioASesionByID(AttendanceDto dto){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Session session = sessionRepository.findById(dto.getSession()).orElseThrow(()->new EntityNotFoundException("Sesion no encontrada"));
        User user = userRepository.findById(dto.getUser()).orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        Attendance newAttendance = new Attendance();

        newAttendance.setSession(session);
        newAttendance.setUser(user);
        attendanceRepository.save(newAttendance);
        dto.setId_attendance(newAttendance.getId_attendance());
        

        respuesta.setExito(true);
        respuesta.getDatos().add(dto);
        respuesta.setMensaje("Usuario "+user.getId_user()+" inscrito a la conferencia "+session.getId_session());
        return respuesta;
    }

    public RespuestaGenerica eliminarUsuarioDeSesionByID(AttendanceDto dto){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Session session = sessionRepository.findById(dto.getSession()).orElseThrow(()->new EntityNotFoundException("Sesion no encontrada"));
        User user = userRepository.findById(dto.getUser()).orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));
        List<Attendance> attendanceToDeleteUser = attendanceRepository.findBySessionAndUser(session, user);

        for (Attendance attendance : attendanceToDeleteUser) {
            attendanceRepository.delete(attendance);
        }

        respuesta.setExito(true);
        respuesta.getDatos().add(dto);
        respuesta.setMensaje("Usuario " + dto.getUser() +" eliminado de la sesion "+dto.getSession());
        return respuesta;
    }

    public RespuestaGenerica obtenerAsistentes(Long id_session){
        RespuestaGenerica respuesta = new RespuestaGenerica();
        Session session = sessionRepository.findById(id_session).orElseThrow(()->new EntityNotFoundException("Sesion no encontrada"));

        List<Attendance> attendanceList = attendanceRepository.findBySession(session);
        List<AttendanceDto> attendanceDtoList = new ArrayList<>();

        for (Attendance attendance : attendanceList) {
            AttendanceDto attendanceDto = new AttendanceDto();
            attendanceDto.setId_attendance(attendance.getId_attendance());
            attendanceDto.setSession(attendance.getSession().getId_session());
            attendanceDto.setUser(attendance.getUser().getId_user());
            attendanceDtoList.add(attendanceDto);
        }

        respuesta.setExito(true);
        respuesta.getDatos().add(attendanceDtoList);
        respuesta.setMensaje("Lista de asistentes para la conferencia con id "+ session.getId_session());

        return respuesta;
    }


    
}
