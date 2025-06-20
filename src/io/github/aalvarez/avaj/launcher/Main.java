package io.github.aalvarez.avaj.launcher;

import java.nio.file.Path;

public class Main {
    public static void
    main(String[] args) {
        Scenario scenario = ScenarioParser.parseScenario(Path.of(args[0]));
    }
}