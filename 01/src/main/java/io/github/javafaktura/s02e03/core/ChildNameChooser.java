package io.github.javafaktura.s02e03.core;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChildNameChooser {
    private final ChildNameProvider childNameProvider;

    public ChildNameChooser(ChildNameProvider childNameProvider) {
        this.childNameProvider = childNameProvider;
    }

    public Optional<String> getRandom(Gender gender, Popularity popularity) {
        List<ChildName> fullList = childNameProvider.load();
        List<ChildName> filtered = filter(fullList, gender, popularity);
        Collections.shuffle(filtered);
        if(filtered.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(filtered.get(0).getName());
        }
    }

    private List<ChildName> filter(List<ChildName> fullList, Gender gender, Popularity popularity) {
        return fullList
                .stream()
                .filter(c -> c.getGender() == gender)
                .filter(c -> c.getOccurrences() > popularity.minOccurences)
                .filter(c -> c.getOccurrences() < popularity.maxOccurences)
                .collect(Collectors.toList());
    }
}