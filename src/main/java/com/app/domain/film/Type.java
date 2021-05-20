package com.app.domain.film;

public enum Type {
    IMAX("IMAX"), D3("3D"), D2("2D");

    private String alias;

    Type(String alias) {
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
