package io.github.javafaktura.s02e03.child.core.service;

import io.github.javafaktura.s02e03.child.core.model.Gender;
import io.github.javafaktura.s02e03.child.core.provider.ChildNameStatsProvider;
import io.github.javafaktura.s02e03.child.core.model.ChildNameStats;
import io.github.javafaktura.s02e03.child.core.model.ParentPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ChildNameMemoryService implements ChildNameService{
    private List<ChildNameStats> stats;

    public ChildNameMemoryService(ChildNameStatsProvider childNameStatsProvider) {
        this.stats = childNameStatsProvider.load();
    }

    public List<ChildNameStats> getAll() {
        return stats;
    }

    public List<ChildNameStats> getAll(ParentPreferences preferences) {
        return filter(stats, preferences.asPredicates());
    }

    public int countAll() {
        return stats.size();
    }

    public ChildNameStats add(String name) {
        for (ChildNameStats childNameStats : stats) {
            if (childNameStats.getName().equalsIgnoreCase(name)) {
                childNameStats.incrementOccurrences();
                return childNameStats;
            }
        }
        ChildNameStats newName = new ChildNameStats(name.toUpperCase(), 1, name.toUpperCase().endsWith("A") ? Gender.FEMALE : Gender.MALE);
        stats.add(newName);
        return newName;
    }

    public ChildNameStats getRandom() {
        return getShuffledCopy(stats).get(0);
    }

    public ChildNameStats getRandom(ParentPreferences preferences) {
        List<ChildNameStats> filtered = filter(getShuffledCopy(stats), preferences.asPredicates());
        return filtered.get(0);
    }

    public ChildNameStats lookFor(String name) {
        return stats.stream()
                .filter(c -> c.getName().equals(name.toUpperCase()))
                .findAny()
                .orElse(new ChildNameStats(name, 0, Gender.fromName(name)));
    }

    private List<ChildNameStats> filter(List<ChildNameStats> fullList, List<Predicate<ChildNameStats>> predicates) {
        return fullList
                .stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private List<ChildNameStats> getShuffledCopy(List<ChildNameStats> source) {
        List<ChildNameStats> destination = new ArrayList<>(source);
        Collections.copy(destination, source);
        Collections.shuffle(destination);
        return destination;
    }
}