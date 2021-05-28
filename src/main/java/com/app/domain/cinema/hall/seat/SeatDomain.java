package com.app.domain.cinema.hall.seat;

import com.app.domain.cinema.hall.HallDomain;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "seat")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SeatDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull private int rowNumber;
    @NonNull private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private HallDomain hall;

    @ElementCollection
    @CollectionTable(name = "seat_status",
            joinColumns = {@JoinColumn(name = "place_id", referencedColumnName = "id")})
    @MapKeyTemporal(TemporalType.TIMESTAMP)
    @Column(name = "status")
    private Map<Date, SeatStatusType> seatStatus;



}
