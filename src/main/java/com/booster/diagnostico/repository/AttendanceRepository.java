package com.booster.diagnostico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booster.diagnostico.data.Attendance;
import com.booster.diagnostico.data.Session;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    
    List<Attendance> findBySession(Session session);
}
