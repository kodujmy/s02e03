package io.github.javafaktura.s02e03.core;

import java.util.Objects;

public class ChildName {
    private String name;
    private int occurrences;
    private Gender gender;

    public ChildName(String name, int occurrences, Gender gender) {
        this.name = name;
        this.occurrences = occurrences;
        this.gender = gender;
    }

    public void incrementOccurrences() {
        this.occurrences++;
    }

    public String getName() {
        return name;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildName childName = (ChildName) o;
        return occurrences == childName.occurrences &&
                Objects.equals(name, childName.name) &&
                gender == childName.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, occurrences, gender);
    }

    @Override
    public String toString() {
        return "ChildName{" +
                "name='" + name + '\'' +
                ", minimalOccurences=" + occurrences +
                ", gender=" + gender +
                '}';
    }
}
