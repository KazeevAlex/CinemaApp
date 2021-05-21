package com.app.domain.cinema.hall;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.CinemaDomain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hall")
public class HallDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String schemaImage;

    @Column(name = "top_banner")
    private String topBanner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hall_gallery_images", joinColumns = @JoinColumn(name = "hall_id"))
    @Column(name = "gallery_image")
    private Set<String> galleryImages;

    @OneToMany(mappedBy = "hall")
    private Set<PlaceDomain> places;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private CinemaDomain cinema;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))

    })
    private SeoBlock seoBlock;
}
