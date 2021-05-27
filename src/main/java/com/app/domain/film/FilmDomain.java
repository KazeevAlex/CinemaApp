package com.app.domain.film;

import com.app.domain.Domain;
import com.app.domain.SeoBlock;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film")
@NoArgsConstructor
@Getter
@Setter
public class FilmDomain extends Domain {

    @Column(name = "trailer_link")
    private String trailerLink;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_type", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    public FilmDomain(
            @NonNull String name, @NonNull String description, @NonNull String mainImage,
            @NonNull Set<String> galleryImages, @NonNull SeoBlock seoBlock,
            String trailerLink, Set<Type> types
    ) {
        super(name, description, mainImage, galleryImages, seoBlock);
        this.trailerLink = trailerLink;
        this.types = types;
    }
}
    /*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
    */