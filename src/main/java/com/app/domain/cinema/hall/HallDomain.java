package com.app.domain.cinema.hall;

import com.app.domain.SeoBlock;
import com.app.domain.cinema.CinemaDomain;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hall")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class HallDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private String name;
    @NonNull private String description;
    @NonNull private String schemaImage;

    @Column(name = "top_banner")
    @NonNull private String topBanner;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hall_gallery_images", joinColumns = @JoinColumn(name = "hall_id"))
    @Column(name = "gallery_image")
    @NonNull private Set<String> galleryImages;

    @OneToMany(mappedBy = "hall")
    @NonNull private Set<PlaceDomain> places;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    @NonNull private CinemaDomain cinema;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))

    })
    @NonNull private SeoBlock seoBlock;
}
