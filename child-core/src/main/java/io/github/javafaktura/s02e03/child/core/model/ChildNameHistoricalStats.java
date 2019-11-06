package io.github.javafaktura.s02e03.child.core.model;

import java.time.Year;
import java.util.Map;
import java.util.Objects;

public class ChildNameHistoricalStats {
    private String name;
    private Gender gender;
    private Map<Year, Integer> historicalStats;


    public ChildNameHistoricalStats(String name, Gender gender, Map<Year, Integer> historicalStats) {
        this.name = name;
        this.gender = gender;
        this.historicalStats = historicalStats;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Map<Year, Integer> getHistoricalStats() {
        return historicalStats;
    }

    public int getStatsForYear(Year year) {
        return historicalStats.getOrDefault(year, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildNameHistoricalStats that = (ChildNameHistoricalStats) o;
        return Objects.equals(name, that.name) &&
                gender == that.gender &&
                Objects.equals(historicalStats, that.historicalStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, historicalStats);
    }
}
