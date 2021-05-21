package com.app.domain.cinema;

import javax.persistence.*;
import java.util.Set;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String build;
    private String email;
    private String mainPhone;
    private String additionalPhone;
    private String mapCoordinate;

    /*@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cinema_booking_phones", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "booking_phone")
    private Set<String> bookingPhones;*/

    public Address() {
    }

    public Address(String city, String street, String build, String email, String mainPhone, String additionalPhone, String mapCoordinate) {
        this.city = city;
        this.street = street;
        this.build = build;
        this.email = email;
        this.mainPhone = mainPhone;
        this.additionalPhone = additionalPhone;
        this.mapCoordinate = mapCoordinate;
    }

    //Getter and Setter

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

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAdditionalPhone() {
        return additionalPhone;
    }

    public void setAdditionalPhone(String additionalPhone) {
        this.additionalPhone = additionalPhone;
    }

    public String getMapCoordinate() {
        return mapCoordinate;
    }

    public void setMapCoordinate(String mapCoordinate) {
        this.mapCoordinate = mapCoordinate;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    /*public Set<String> getBookingPhones() {
        return bookingPhones;
    }

    public void setBookingPhones(Set<String> bookingPhones) {
        this.bookingPhones = bookingPhones;
    }*/
}
