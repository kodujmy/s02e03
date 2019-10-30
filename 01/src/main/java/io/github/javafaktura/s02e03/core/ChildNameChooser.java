package io.github.javafaktura.s02e03.core;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ChildNameChooser {
    private final ChildNameProvider childNameProvider;

    public ChildNameChooser(ChildNameProvider childNameProvider) {
        this.childNameProvider = childNameProvider;
    }

    public String getRandom() {
        List<ChildName> fullList = childNameProvider.load();
        Collections.shuffle(fullList);
        return fullList.get(0).getName();
    }

    public String getRandom(Gender gender) {
        List<ChildName> fullList = childNameProvider.load();
        Collections.shuffle(fullList);
        List<ChildName> filtered = filter(fullList, gender);
        return fullList.get(0).getName();
    }

    public String getRandom(Gender gender, Popularity popularity) {
        List<ChildName> fullList = childNameProvider.load();
        List<ChildName> filtered = filter(fullList, gender, popularity);
        Collections.shuffle(filtered);
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
}