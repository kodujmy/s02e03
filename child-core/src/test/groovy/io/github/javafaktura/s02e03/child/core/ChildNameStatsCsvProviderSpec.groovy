package io.github.javafaktura.s02e03.child.core

import io.github.javafaktura.s02e03.child.core.model.ChildNameStats
import io.github.javafaktura.s02e03.child.core.model.Gender
import io.github.javafaktura.s02e03.child.core.provider.ChildNameStatsCsvStatsProvider
import spock.lang.Specification
import spock.lang.Unroll

class ChildNameStatsCsvProviderSpec extends Specification {

    private static final String CHILD_NAMES = "4_child_names.csv"

    @Unroll
    def "When csv is loaded, first record has name ANTONI "() {
        given:
        ChildNameStatsCsvStatsProvider csvProvider = new ChildNameStatsCsvStatsProvider(CHILD_NAMES);
        List<ChildNameStats> childNames = csvProvider.load()
        expect:
        new ChildNameStats("ANTONI", 4247, Gender.MALE) == childNames.get(0)
    }
}