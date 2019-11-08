package io.github.javafaktura.s02e03.child.core.provider;

import io.github.javafaktura.s02e03.child.core.model.ChildNameHistoricalStats;

import java.util.List;

public interface ChildNameHistoricalStatsProvider {
    List<ChildNameHistoricalStats> load();
}
