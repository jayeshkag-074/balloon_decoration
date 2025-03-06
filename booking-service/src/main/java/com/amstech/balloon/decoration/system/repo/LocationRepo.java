package com.amstech.balloon.decoration.system.repo;




import org.springframework.data.jpa.repository.JpaRepository;

import com.amstech.balloon.decoration.system.entity.Location;

public interface LocationRepo extends JpaRepository<Location, Integer> {

}
