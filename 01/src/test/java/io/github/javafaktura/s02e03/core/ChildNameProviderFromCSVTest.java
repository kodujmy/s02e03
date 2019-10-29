package io.github.javafaktura.s02e03.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildNameProviderFromCSVTest {

    private static final String CHILD_NAMES = "src/test/resources/4_child_names.csv";

    @Test
    @DisplayName("First loaded child name should be Antoni")
    void firstLoadedChildShouldBeAntoni() {

        ChildNameProviderFromCSV csvProvider = new ChildNameProviderFromCSV(CHILD_NAMES);
        List<ChildName> childNames = csvProvider.load();

        assertEquals(new ChildName("ANTONI", 4247, Gender.MALE), childNames.get(0));
    }
}
