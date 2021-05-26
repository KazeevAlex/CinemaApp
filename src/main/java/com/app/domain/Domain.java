package com.app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public abstract class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String description;

    @Column(name = "main_image")
    @NonNull
    private String mainImage; // also Top Banner

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gallery_images", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "gallery_image")
    @NonNull
    private Set<String> galleryImages;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))
    })
    @NonNull
    private SeoBlock seoBlock;
}
