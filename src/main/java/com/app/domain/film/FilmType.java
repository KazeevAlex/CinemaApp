package com.app.domain.film;

public enum FilmType {
    IMAX("IMAX"), D3("3D"), D2("2D");

    private final String alias;

    FilmType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    /*@Override
    public String toString() {
        return alias;
    }*/
}
