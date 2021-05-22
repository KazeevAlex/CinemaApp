package com.app.repos;

import com.app.domain.cinema.CinemaDomain;
import com.app.domain.film.FilmDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepo extends CrudRepository<CinemaDomain, Long> {

    Page<CinemaDomain> findAll(Pageable pageable);

}
