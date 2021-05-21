package com.app.repos;

import com.app.domain.cinema.CinemaDomain;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepo extends CrudRepository<CinemaDomain, Long> {

}
