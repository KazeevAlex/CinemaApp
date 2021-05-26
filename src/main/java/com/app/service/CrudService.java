package com.app.service;

import com.app.domain.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService {

    void save(Domain domain);

    Domain getById(Long Id);

    Page<Domain> getAll(Pageable pageable);

    void deleteById(Long cinemaId);
}
