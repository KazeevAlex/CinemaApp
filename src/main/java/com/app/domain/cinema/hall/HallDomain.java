package com.app.domain.cinema.hall;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.cinema.CinemaDomain;
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

    @OneToMany(mappedBy = "hall")
    private Set<PlaceDomain> places;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private CinemaDomain cinema;

    public HallDomain(
            @NonNull String name, @NonNull String description, @NonNull String mainImage,
            @NonNull Set<String> galleryImages, @NonNull SeoBlock seoBlock,
            String schemaImage, Set<PlaceDomain> places, CinemaDomain cinema
    ) {
        super(name, description, mainImage, galleryImages, seoBlock);
        this.schemaImage = schemaImage;
        this.places = places;
        this.cinema = cinema;
    }
}
