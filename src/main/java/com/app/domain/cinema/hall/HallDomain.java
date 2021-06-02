package com.app.domain.cinema.hall;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.cinema.CinemaDomain;
import com.app.domain.cinema.hall.seat.HallSeatsBuilder;
import com.app.domain.cinema.hall.seat.SeatDomain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hall")
@NoArgsConstructor
@Getter
@Setter
public class HallDomain extends Domain {

    private String schemaImage;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.REMOVE)
    private Set<SeatDomain> seats;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private CinemaDomain cinema;

    public HallDomain(
            @NonNull String name, @NonNull String description, @NonNull String mainImage,
            @NonNull Set<String> galleryImages, @NonNull SeoBlock seoBlock,
            String schemaImage, CinemaDomain cinema
    ) {
        super(name, description, mainImage, galleryImages, seoBlock);
        this.schemaImage = schemaImage;
        this.cinema = cinema;
        /*
        HallSeatsBuilder hallSeatsBuilder = new HallSeatsBuilder();
        switch (HallType.valueOf(hallType)) {
            case ECONOMY:
                this.seats = hallSeatsBuilder.createHallSeats(20);
                break;
            case COMFORT:
                this.seats = hallSeatsBuilder.createHallSeats(12);
                break;
            case BUSINESS:
                this.seats = hallSeatsBuilder.createHallSeats(6);
        }
        */
    }
}
