package com.booster.diagnostico.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booster.diagnostico.data.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}