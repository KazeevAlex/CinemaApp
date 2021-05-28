package com.app.domain.cinema.hall.seat;

import java.util.HashSet;
import java.util.Set;

public class HallSeatsBuilder {
    public Set<SeatDomain> createHallSeats(int numberOfRows) {
        Set<SeatDomain> hallSeats = new HashSet<>();
        for (int i = 1; i <= numberOfRows; i++)
            hallSeats.addAll(createRow(i, numberOfRows));

        return hallSeats;
    }

    private Set<SeatDomain> createRow(int rowNumber, int numberOfSeats) {
        Set<SeatDomain> seatRow = new HashSet<>();
        for (int i = 1; i <= numberOfSeats; i++)
            seatRow.add(new SeatDomain(rowNumber, i));

        return seatRow;
    }
}
