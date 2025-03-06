package com.amstech.balloon.decoration.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.balloon.decoration.system.entity.Status;

public interface StatusRepo extends JpaRepository<Status, Integer> {

}
