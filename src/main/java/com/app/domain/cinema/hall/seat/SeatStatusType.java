package com.app.domain.cinema.hall.seat;

public enum SeatStatusType {
    FREE("Free"), BOOKED("Booked"), SOLD("Sold");

    private final String alias;

    SeatStatusType(String alias) {
        this.alias = alias;
    }
}
