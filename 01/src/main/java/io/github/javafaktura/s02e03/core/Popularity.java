package io.github.javafaktura.s02e03.core;

public enum Popularity {
    MOST_POPULAR(500, Integer.MAX_VALUE),
    POPULAR(100, 499),
    UNIQUE(10, 99),
    VERY_RARE(0, 9);

    final int minOccurences;
    final int maxOccurences;


    Popularity(int minOccurences, int maxOccurences) {
        this.minOccurences = minOccurences;
        this.maxOccurences = maxOccurences;
    }
}
