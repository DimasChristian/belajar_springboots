package com.example.springmbud.repo;

import com.example.springmbud.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<user, Long> {

}
