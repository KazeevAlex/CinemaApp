package com.app.domain.cinema;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.hall.HallDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema")
public class CinemaDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String conditions;

    private String logo;

    @Column(name = "top_banner")
    private String topBanner;

    @OneToMany (mappedBy = "cinema")
    private Set<HallDomain> halls;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cinema_gallery_images", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "gallery_image")
    private Set<String> galleryImages;



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mapCoordinate", column = @Column(name = "map_coordinate"))

    })
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))

    })
    private SeoBlock seoBlock;



}
