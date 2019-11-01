package io.github.javafaktura.s02e03.child.core


import io.github.javafaktura.s02e03.child.core.model.ChildNameStats
import io.github.javafaktura.s02e03.child.core.model.Gender
import io.github.javafaktura.s02e03.child.core.model.ParentPreferences
import io.github.javafaktura.s02e03.child.core.model.Popularity
import io.github.javafaktura.s02e03.child.core.provider.ChildNameStatsProvider
import io.github.javafaktura.s02e03.child.core.service.ChildNameMemoryService
import io.github.javafaktura.s02e03.child.core.service.ChildNameService
import spock.lang.Specification
import spock.lang.Unroll

class ChildNameServiceSpec extends Specification {

    private ChildNameService childNameService = new ChildNameMemoryService(new ChildNameStatsProviderFromMemory())

    @Unroll
    def "When gender is #gender and popularity is #popularity should return name #name "() {
        expect:
            name == childNameService.getRandom(new ParentPreferences(gender, popularity)).name
        where:
            gender        | popularity              || name
            Gender.MALE   | Popularity.MOST_POPULAR || "ANTONI"
            Gender.MALE   | Popularity.VERY_RARE    || "BEN"
            Gender.FEMALE | Popularity.POPULAR      || "KAMILA"
    }

    class ChildNameStatsProviderFromMemory implements ChildNameStatsProvider {
        @Override
        List<ChildNameStats> load() {
            return [
                    new ChildNameStats("ANTONI", 4247, Gender.MALE),
                    new ChildNameStats("BEN", 5, Gender.MALE),
                    new ChildNameStats("JULIAN", 99, Gender.MALE),
                    new ChildNameStats("ZUZANNA", 4251, Gender.FEMALE),
                    new ChildNameStats("KAMILA", 186, Gender.FEMALE),

            ]
        }
    }
}
