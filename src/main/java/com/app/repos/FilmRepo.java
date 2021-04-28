package com.app.repos;

import com.app.domain.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepo extends CrudRepository<Film, Long> {

    List<Film> findByName(String name);
}
