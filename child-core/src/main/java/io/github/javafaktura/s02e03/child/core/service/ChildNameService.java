package io.github.javafaktura.s02e03.child.core.service;

import io.github.javafaktura.s02e03.child.core.model.ChildNameStats;
import io.github.javafaktura.s02e03.child.core.model.ParentPreferences;

import java.util.List;

public interface ChildNameService {
    List<ChildNameStats> getAll(ParentPreferences preferences);
    int countAll();
    ChildNameStats add(String name);
    ChildNameStats getRandom(ParentPreferences preferences);
    ChildNameStats lookFor(String name);
}
