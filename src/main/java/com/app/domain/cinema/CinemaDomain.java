package com.app.domain.cinema;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import com.app.domain.cinema.hall.HallDomain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema")
@NoArgsConstructor
@Getter
@Setter
public class CinemaDomain extends Domain {

    private String conditions;
    private String logo;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private Set<HallDomain> halls;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mapCoordinate", column = @Column(name = "map_coordinate")),
            @AttributeOverride(name = "mainPhone", column = @Column(name = "main_phone")),
            @AttributeOverride(name = "additionalPhone", column = @Column(name = "additional_phone"))
    })
    private Address address;

    public CinemaDomain(
            @NonNull String name, @NonNull String description, @NonNull String mainImage,
            @NonNull Set<String> galleryImages, @NonNull SeoBlock seoBlock, String conditions,
            String logo, Address address)
    {
        super(name, description, mainImage, galleryImages, seoBlock);
        this.conditions = conditions;
        this.logo = logo;
        this.address = address;
    }
}
