package com.app.domain.cinema.hall;

import javax.persistence.*;

@Entity
@Table(name = "place")
public class PlaceDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int row;
    private int place;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private HallDomain hall;
}
