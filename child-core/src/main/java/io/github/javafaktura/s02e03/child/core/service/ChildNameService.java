package io.github.javafaktura.s02e03.child.core.service;

import io.github.javafaktura.s02e03.child.core.model.ChildNameHistoricalStats;
import io.github.javafaktura.s02e03.child.core.model.ChildNameStats;
import io.github.javafaktura.s02e03.child.core.model.ParentPreferences;

import java.time.Year;
import java.util.List;

public interface ChildNameService {
    List<ChildNameStats> getAll(ParentPreferences preferences);
    int countAllOccurences();
    ChildNameStats add(String name);
    ChildNameStats getRandom(ParentPreferences preferences);
    ChildNameStats lookFor(String name);

    ChildNameHistoricalStats getHistoricalStats(String name);
    Integer getHistoricalOccurences(String name, Year year);
}
