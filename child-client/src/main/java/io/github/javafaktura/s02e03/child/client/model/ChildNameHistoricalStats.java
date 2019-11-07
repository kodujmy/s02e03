package io.github.javafaktura.s02e03.child.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class ChildNameHistoricalStats {
    private String name;
    private Gender gender;
    private Map<Integer, Integer> historicalStats;


    public ChildNameHistoricalStats(String name, Gender gender, Map<Integer, Integer> historicalStats) {
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

    public Map<Integer, Integer> getHistoricalStats() {
        return historicalStats;
    }

    public int getStatsForYear(Integer year) {
        return historicalStats.getOrDefault(year, 0);
    }

    @JsonIgnore
    public Integer getLastMostPopularYear() {
        return getMaxEntry(historicalStats).getKey();
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
                Objects.equals(historicalStats, that.historicalStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, historicalStats);
    }

    public ChartData toChartData() {
        Integer[] keyArray = historicalStats.keySet().toArray(new Integer[historicalStats.keySet().size()]);
        Integer[] valueArray = historicalStats.values().toArray(new Integer[historicalStats.values().size()]);
        return new ChartData(keyArray, valueArray);
    }
}
