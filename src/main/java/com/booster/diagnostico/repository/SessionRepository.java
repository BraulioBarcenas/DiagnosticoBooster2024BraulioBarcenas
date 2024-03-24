package com.booster.diagnostico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booster.diagnostico.data.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long>{

}
