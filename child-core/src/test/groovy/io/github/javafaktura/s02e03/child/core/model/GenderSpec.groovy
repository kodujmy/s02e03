package io.github.javafaktura.s02e03.child.core.model

import io.github.javafaktura.s02e03.child.core.model.Gender
import spock.lang.Specification
import spock.lang.Unroll

class GenderSpec extends Specification {

    @Unroll
    def "For given name = #name should detect right gender = #expectedGender"() {
        expect:
        expectedGender == Gender.fromName(name)
        where:
        name            || expectedGender
        "JANUSZ"        || Gender.MALE
        "ANNA"          || Gender.FEMALE
        "JAVAFAKTURA"   || Gender.FEMALE
    }
}
