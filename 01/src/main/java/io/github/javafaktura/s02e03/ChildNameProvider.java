package io.github.javafaktura.s02e03;

import java.io.FileNotFoundException;
import java.util.List;

interface ChildNameProvider {
    List<ChildName> load() throws FileNotFoundException;
}
