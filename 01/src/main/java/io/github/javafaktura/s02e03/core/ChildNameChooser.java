package io.github.javafaktura.s02e03.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ChildNameChooser {
    private List<ChildName> storage;

    public ChildNameChooser(ChildNameProvider childNameProvider) {
        this.storage = childNameProvider.load();
    }

    public List<ChildName> getAll() {
        return storage;
    }

    public List<ChildName> getAll(ChildNameParentPreferences preferences) {
        return filter(storage, preferences.asPredicates());
    }

    public int countAll() {
        return storage.size();
    }

    public ChildName add(String name) {
        for (ChildName childName : storage) {
            if(childName.getName().equalsIgnoreCase(name)) {
                childName.incrementOccurrences();
                return childName;
            }
        }
        ChildName newName = new ChildName(name.toUpperCase(), 1, name.toUpperCase().endsWith("A") ? Gender.FEMALE : Gender.MALE);
        storage.add(newName);
        return newName;
    }

    public ChildName getRandom() {
        return getShuffledCopy(storage).get(0);
    }

    public ChildName getRandom(ChildNameParentPreferences preferences) {
        List<ChildName> filtered = filter(getShuffledCopy(storage), preferences.asPredicates());
        return filtered.get(0);
    }

    private List<ChildName> filter(List<ChildName> fullList, List<Predicate<ChildName>> predicates) {
        return fullList
                .stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private List<ChildName> getShuffledCopy(List<ChildName> source) {
        List<ChildName> destination = new ArrayList<>(source);
        Collections.copy(destination, source);
        Collections.shuffle(destination);
        return destination;
    }
}