package io.github.javafaktura.s02e03;

public enum Popularity {
    MOST_POPULAR(500), POPULAR(100), UNIQUE(10), VERY_RARE(0);

    final int minimalOccurences;

    Popularity(int occurences) {
        this.minimalOccurences = occurences;
    }
}
