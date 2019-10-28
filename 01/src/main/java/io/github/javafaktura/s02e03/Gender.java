package io.github.javafaktura.s02e03;

enum Gender {
    MALE,FEMALE;

    public static Gender fromSymbol(String symbol) {
        if(symbol.equals("M")) {
            return MALE;
        } else {
            return FEMALE;
        }
    }
}
