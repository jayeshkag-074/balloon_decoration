package com.amstech.balloon.decoration.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amstech.balloon.decoration.system.entity.Decoration;

public interface DecorationRepo extends JpaRepository<Decoration, Integer>{

	@Query("select d from Decoration d where d.status.id =:continueStatusId")
	List<Decoration> findAllByContinueStatusId(@Param("continueStatusId") Integer continueStatusId);
	
	@Query("SELECT d FROM Decoration d WHERE d.name = :name and d.status.id =:continueStatusId")
	List<Decoration> searchBy(@Param("name") String name,@Param("continueStatusId") Integer continueStatusId);
}
