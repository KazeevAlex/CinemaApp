package com.app.domain.film;

import com.app.domain.SeoBlock;
import com.app.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FilmDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull private String name;

    @NonNull private String description;

    @Column(name = "main_image")
    @NonNull private String mainImage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_gallery_images", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "gallery_image")
    @NonNull private Set<String> galleryImages;

    @Column(name = "trailer_link")
    @NonNull private String trailerLink;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_type", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    @NonNull private Set<Type> types;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))

    })
    @NonNull private SeoBlock seoBlock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public FilmDomain(@NonNull String name, @NonNull String description, User author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
}
