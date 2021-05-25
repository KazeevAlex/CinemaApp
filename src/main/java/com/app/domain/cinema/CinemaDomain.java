package com.app.domain.cinema;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.hall.HallDomain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class CinemaDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String conditions;
    @NonNull private String logo;

    @Column(name = "top_banner")
    @NonNull private String topBanner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cinema_gallery_images", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "gallery_image")
    @NonNull private Set<String> galleryImages;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private Set<HallDomain> halls;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mapCoordinate", column = @Column(name = "map_coordinate")),
            @AttributeOverride(name = "mainPhone", column = @Column(name = "main_phone")),
            @AttributeOverride(name = "additionalPhone", column = @Column(name = "additional_phone"))
    })
    @NonNull private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))
    })
    @NonNull private SeoBlock seoBlock;
}
