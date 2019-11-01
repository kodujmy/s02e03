package io.github.javafaktura.s02e03.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ChildNameParentPreferences {

    private Gender gender;
    private Popularity popularity;

    public List<Predicate<ChildName>> asPredicates() {
        List<Predicate<ChildName>> predicates = new ArrayList<>();

        if (gender != null) {
            predicates.add(c -> c.getGender() == gender);
        }
        if (popularity != null) {
            predicates.add(c -> c.getOccurrences() > popularity.getMinOccurences());
            predicates.add(c -> c.getOccurrences() < popularity.getMaxOccurences());
        }
        return predicates;
    }

    public ChildNameParentPreferences(Gender gender) {
        this.gender = gender;
    }

    public ChildNameParentPreferences(Gender gender, Popularity popularity) {
        this.gender = gender;
        this.popularity = popularity;
    }

    public Gender getGender() {
        return gender;
    }

    public Popularity getPopularity() {
        return popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildNameParentPreferences)) return false;
        ChildNameParentPreferences that = (ChildNameParentPreferences) o;
        return gender == that.gender &&
                popularity == that.popularity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, popularity);
    }

    @Override
    public String toString() {
        return "ChildNameParentPreferences{" +
                "gender=" + gender +
                ", popularity=" + popularity +
                '}';
    }
}
