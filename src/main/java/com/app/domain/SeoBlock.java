package com.app.domain;

import javax.persistence.Embeddable;

@Embeddable
public class SeoBlock {
    private String url;
    private String title;
    private String keywords;
    private String description;

    public SeoBlock() {
    }

    public SeoBlock(String url, String title, String keywords, String description) {
        this.url = url;
        this.title = title;
        this.keywords = keywords;
        this.description = description;
    }

    /*Setter/Getter*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
