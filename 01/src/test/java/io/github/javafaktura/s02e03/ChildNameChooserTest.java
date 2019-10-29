package io.github.javafaktura.s02e03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildNameChooserTest {

    private static final String CHILD_NAMES = "src/test/resources/4_child_names.csv";

    private ChildNameChooser childNameChooser = new ChildNameChooser(new ChildNameProviderFromCSV(CHILD_NAMES));


    @Test
    @ParameterizedTest(name = "For given gender = {0} and popularity = {1} should return child name = {2}")
    @CsvSource({
            "MALE,     MOST_POPULAR,    ANTONI",
            "MALE,     VERY_RARE,       BEN"
    })
    void firstLoadedChildShouldBeAntoni(Gender gender, Popularity popularity, String expectedName) {

        assertEquals(expectedName, childNameChooser.getRandom(gender, popularity).get());
    }
}
