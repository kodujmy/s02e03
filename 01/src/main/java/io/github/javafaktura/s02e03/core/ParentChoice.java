package io.github.javafaktura.s02e03.core;

import java.util.Objects;

public class ParentChoice {
    private String name;

    public ParentChoice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentChoice)) return false;
        ParentChoice that = (ParentChoice) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
