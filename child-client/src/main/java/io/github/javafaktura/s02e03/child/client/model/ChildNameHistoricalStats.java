package io.github.javafaktura.s02e03.child.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class ChildNameHistoricalStats {
    private String name;
    private Gender gender;
    private Map<Integer, Integer> stats;


    public ChildNameHistoricalStats(String name, Gender gender, Map<Integer, Integer> stats) {
        this.name = name;
        this.gender = gender;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Map<Integer, Integer> getStats() {
        return stats;
    }

    public int getStatsForYear(Integer year) {
        return stats.getOrDefault(year, 0);
    }

    @JsonIgnore
    public Integer getLastMostPopularYear() {
        return getMaxEntry(stats).getKey();
    }

    private static Map.Entry<Integer, Integer> getMaxEntry(Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> maxEntry = null;
        Integer max = Collections.max(map.values());

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();

            if(null != value && max == value) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildNameHistoricalStats that = (ChildNameHistoricalStats) o;
        return Objects.equals(name, that.name) &&
                gender == that.gender &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, stats);
    }

    public ChartData toChartData() {
        Integer[] keyArray = stats.keySet().toArray(new Integer[stats.keySet().size()]);
        Integer[] valueArray = stats.values().toArray(new Integer[stats.values().size()]);
        return new ChartData(keyArray, valueArray);
    }
}
