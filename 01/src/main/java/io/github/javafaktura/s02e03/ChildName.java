package io.github.javafaktura.s02e03;

import java.util.Objects;

public class ChildName {
    private String name;
    private int occurences;
    private Gender gender;

    public ChildName(String name, int occurences, Gender gender) {
        this.name = name;
        this.occurences = occurences;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getOccurences() {
        return occurences;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildName childName = (ChildName) o;
        return occurences == childName.occurences &&
                Objects.equals(name, childName.name) &&
                gender == childName.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, occurences, gender);
    }

    @Override
    public String toString() {
        return "ChildName{" +
                "name='" + name + '\'' +
                ", minimalOccurences=" + occurences +
                ", gender=" + gender +
                '}';
    }
}
