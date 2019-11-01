package io.github.javafaktura.s02e03.core

import spock.lang.Specification
import spock.lang.Unroll

class ChildNameChooserSpec extends Specification {

    private ChildNameChooser childNameChooser = new ChildNameChooser(new ChildNameProviderFromMemory())

    @Unroll
    def "When gender is #gender and popularity is #popularity should return name #name "() {
        expect:
            name == childNameChooser.getRandom(new ChildNameParentPreferences(gender, popularity)).name
        where:
            gender        | popularity                || name
            Gender.MALE   | Popularity.MOST_POPULAR   || "ANTONI"
            Gender.MALE   | Popularity.VERY_RARE      || "BEN"
            Gender.FEMALE | Popularity.POPULAR        || "KAMILA"
    }

    class ChildNameProviderFromMemory implements ChildNameProvider {
        @Override
        List<ChildName> load() {
            return [
                new ChildName("ANTONI", 4247, Gender.MALE),
                new ChildName("BEN", 5, Gender.MALE),
                new ChildName("JULIAN", 99, Gender.MALE),
                new ChildName("ZUZANNA", 4251, Gender.FEMALE),
                new ChildName("KAMILA", 186, Gender.FEMALE),

            ]
        }
    }
}
