package com.app.service.cinema;

import com.app.domain.cinema.hall.HallDomain;
import com.app.domain.cinema.hall.HallType;
import com.app.domain.cinema.hall.seat.HallSeatsBuilder;
import com.app.domain.cinema.hall.seat.SeatDomain;
import com.app.repos.cinema.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SeatService {

    SeatRepo seatRepo;

    @Autowired
    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    public void createAndSaveHallSeats(HallDomain hallDomain, String hallType) {

        Set<SeatDomain> seats = null;
        HallSeatsBuilder hallSeatsBuilder = new HallSeatsBuilder();

        switch (HallType.valueOf(hallType)) {
            case ECONOMY:
                seats = hallSeatsBuilder.createHallSeats(20);
                break;
            case COMFORT:
                seats = hallSeatsBuilder.createHallSeats(12);
                break;
            case BUSINESS:
                seats = hallSeatsBuilder.createHallSeats(6);
        }

        seats.forEach((seat) -> {
            seat.setHall(hallDomain);
            seatRepo.save(seat);
        });

//        saveSeatSet(seats);
    }

/*

    private void saveSeatSet(Set<SeatDomain> seats) {
        for(SeatDomain seat : seats)
            seatRepo.save(seat);
    }
*/

    public void deleteById(Long id) {
        seatRepo.deleteById(id);
    }
}
