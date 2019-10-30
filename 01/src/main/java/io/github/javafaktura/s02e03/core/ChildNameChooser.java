package io.github.javafaktura.s02e03.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ChildNameChooser {
    private List<ChildName> storage;

    public ChildNameChooser(ChildNameProvider childNameProvider) {
        this.storage = childNameProvider.load();
    }

    public String getRandom() {
        return getShuffledCopy(storage).get(0).getName();
    }

    public String getRandom(Gender gender) {
        List<ChildName> filtered = filter(getShuffledCopy(storage), gender);
        return filtered.get(0).getName();
    }

    public String getRandom(Gender gender, Popularity popularity) {
        List<ChildName> filtered = filter(getShuffledCopy(storage), gender, popularity);
        return filtered.get(0).getName();
    }


    private List<ChildName> filter(List<ChildName> fullList, Gender gender, Popularity popularity) {
        return fullList
                .stream()
                .filter(c -> c.getGender() == gender)
                .filter(c -> c.getOccurrences() > popularity.minOccurences)
                .filter(c -> c.getOccurrences() < popularity.maxOccurences)
                .collect(Collectors.toList());
    }

    private List<ChildName> filter(List<ChildName> fullList, Gender gender) {
        return fullList
                .stream()
                .filter(c -> c.getGender() == gender)
                .collect(Collectors.toList());
    }

    private List<ChildName> getShuffledCopy(List<ChildName> source) {
        List<ChildName> destination = new ArrayList<>(source);
        Collections.copy(destination, source);
        Collections.shuffle(destination);
        return destination;
    }
}