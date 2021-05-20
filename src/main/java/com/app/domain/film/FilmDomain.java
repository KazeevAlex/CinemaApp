package com.app.domain.film;

import com.app.domain.SeoBlock;
import com.app.domain.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "film")
public class FilmDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "main_image")
    private String mainImage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "film_gallery_images", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "gallery_image")
    private Set<String> galleryImages;

    @Column(name = "trailer_link")
    private String trailerLink;

    @ElementCollection(targetClass = Type.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "film_type", joinColumns = @JoinColumn(name = "film_id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "url", column = @Column(name = "seo_url")),
            @AttributeOverride(name = "title", column = @Column(name = "seo_title")),
            @AttributeOverride(name = "keywords", column = @Column(name = "seo_keywords")),
            @AttributeOverride(name = "description", column = @Column(name = "seo_description"))

    })
    private SeoBlock seoBlock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    public FilmDomain() {
    }

    public FilmDomain(String name, Set<Type> types, String trailerLink, String description, SeoBlock seoBlock) {
        this.name = name;
        this.description = description;
        this.trailerLink = trailerLink;
        this.types = types;
        this.seoBlock = seoBlock;
    }

    //    Deprecated, delete
    public FilmDomain(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }


    /*Setter/Getter*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMainImage(String filename) {
        this.mainImage = filename;
    }

    public String getMainImage() {
        return mainImage;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<String> getGalleryImages() {
        return galleryImages;
    }

    public void setGalleryImages(Set<String> additionalImages) {
        this.galleryImages = additionalImages;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public SeoBlock getSeoBlock() {
        return seoBlock;
    }

    public void setSeoBlock(SeoBlock seoBlock) {
        this.seoBlock = seoBlock;
    }
}
