package com.app.domain.cinema.hall;

import lombok.Getter;

@Getter
public enum HallType {
    ECONOMY("Economy"), COMFORT("Comfort"), BUSINESS("Business");

    private final String alias;

    HallType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
