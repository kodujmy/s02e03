package io.github.javafaktura.s02e03;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChildNameChooser {

    private final ChildNameProvider childNameProvider;

    public ChildNameChooser(ChildNameProvider childNameProvider) {
        this.childNameProvider = childNameProvider;
    }

    public Optional<String> getRandom(Gender gender, Popularity popularity) {

        List<ChildName> fullList = childNameProvider.load();

        List<ChildName> filtered = fullList
                .stream()
                .filter(c -> c.getGender() == gender)
                .filter(c -> c.getOccurences() > popularity.minOccurences)
                .filter(c -> c.getOccurences() < popularity.maxOccurences)
                .collect(Collectors.toList());

        Collections.shuffle(filtered);

        if(filtered.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(filtered.get(0).getName());
        }


    }
}
