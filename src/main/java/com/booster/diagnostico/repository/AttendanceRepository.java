package com.booster.diagnostico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booster.diagnostico.data.Attendance;
import com.booster.diagnostico.data.Session;
import com.booster.diagnostico.data.User;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    
    List<Attendance> findBySession(Session session);
    List<Attendance> findBySessionAndUser(Session session, User user);
}
