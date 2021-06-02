package com.app.repos.cinema;

import com.app.domain.cinema.hall.seat.SeatDomain;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepo extends CrudRepository<SeatDomain, Long> {
}
