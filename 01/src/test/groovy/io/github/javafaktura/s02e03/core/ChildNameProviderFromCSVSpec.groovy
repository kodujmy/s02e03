package io.github.javafaktura.s02e03.core

import spock.lang.Specification
import spock.lang.Unroll

class ChildNameProviderFromCSVSpec extends Specification {

    private static final String CHILD_NAMES = "src/test/resources/4_child_names.csv";

    @Unroll
    def "When csv is loaded, first record has name ANTONI "() {
        given:
        ChildNameProviderFromCSV csvProvider = new ChildNameProviderFromCSV(CHILD_NAMES);
        List<ChildName> childNames = csvProvider.load();
        expect:
        new ChildName("ANTONI", 4247, Gender.MALE) == childNames.get(0)
    }
}