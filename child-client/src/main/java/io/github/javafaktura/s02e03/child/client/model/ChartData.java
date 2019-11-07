package io.github.javafaktura.s02e03.child.client.model;

public class ChartData {

    private final Integer[] labels;
    private final Integer[] values;

    public ChartData(Integer[] labels, Integer[] values) {
        this.labels = labels;
        this.values = values;
    }

    public boolean isEmpty() {
        return labels.length == 0 || values.length == 0;
    }

    public Integer[] getLabels() {
        return labels;
    }

    public Integer[] getValues() {
        return values;
    }
}
