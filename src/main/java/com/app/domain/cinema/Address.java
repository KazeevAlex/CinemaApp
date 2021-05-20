package com.app.domain.cinema;

import javax.persistence.*;
import java.util.Set;

@Embeddable
public class Address {

    private boolean active;

    private String city;
    private String street;
    private int build;
    private String email;
    private String mapCoordinate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cinema_booking_phones", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "booking_phone")
    private Set<String> bookingPhones;


    //Getter and Setter
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuild() {
        return build;
    }

    public void setBuild(int build) {
        this.build = build;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMapCoordinate() {
        return mapCoordinate;
    }

    public void setMapCoordinate(String mapCoordinate) {
        this.mapCoordinate = mapCoordinate;
    }

    public Set<String> getBookingPhones() {
        return bookingPhones;
    }

    public void setBookingPhones(Set<String> bookingPhones) {
        this.bookingPhones = bookingPhones;
    }
}
