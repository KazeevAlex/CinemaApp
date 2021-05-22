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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "cinema_gallery_images", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "gallery_image")
    private Set<String> galleryImages;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
    private Set<HallDomain> halls;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "mapCoordinate", column = @Column(name = "map_coordinate")),
            @AttributeOverride(name = "mainPhone", column = @Column(name = "main_phone")),
            @AttributeOverride(name = "additionalPhone", column = @Column(name = "additional_phone"))
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

    public CinemaDomain() {
    }

    public CinemaDomain(String name, String description, String conditions, String logo,
                        String topBanner, Set<String> galleryImages, Address address, SeoBlock seoBlock) {
        this.name = name;
        this.description = description;
        this.conditions = conditions;
        this.logo = logo;
        this.topBanner = topBanner;
        this.galleryImages = galleryImages;
        this.address = address;
        this.seoBlock = seoBlock;
    }

    //getter and setter
    public Long getId() { return id; }

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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTopBanner() {
        return topBanner;
    }

    public void setTopBanner(String topBanner) {
        this.topBanner = topBanner;
    }

    public Set<String> getGalleryImages() {
        return galleryImages;
    }

    public void setGalleryImages(Set<String> galleryImages) {
        this.galleryImages = galleryImages;
    }

    public Set<HallDomain> getHalls() {
        return halls;
    }

    public void setHalls(Set<HallDomain> halls) {
        this.halls = halls;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SeoBlock getSeoBlock() {
        return seoBlock;
    }

    public void setSeoBlock(SeoBlock seoBlock) {
        this.seoBlock = seoBlock;
    }
}
