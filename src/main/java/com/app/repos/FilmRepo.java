package com.app.repos;

import com.app.domain.film.FilmDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepo extends CrudRepository<FilmDomain, Long> {

    Page<FilmDomain> findByName(String name, Pageable pageable);

    Page<FilmDomain> findAll(Pageable pageable);
}
