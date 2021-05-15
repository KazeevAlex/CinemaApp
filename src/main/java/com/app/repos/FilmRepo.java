package com.app.repos;

import com.app.domain.film.FilmDomain;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepo extends CrudRepository<FilmDomain, Long> {

    List<FilmDomain> findByName(String name);
}
