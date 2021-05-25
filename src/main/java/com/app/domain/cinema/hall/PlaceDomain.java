package com.app.domain.cinema.hall;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "place")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PlaceDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull private int row;
    @NonNull private int place;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    @NonNull private HallDomain hall;
}
