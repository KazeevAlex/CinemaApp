package com.app.repos;

import com.app.domain.cinema.CinemaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CinemaRepo extends PagingAndSortingRepository<CinemaDomain, Long> {

    Page<CinemaDomain> findAll(Pageable pageable);

}
