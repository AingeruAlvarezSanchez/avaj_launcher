package io.github.aalvarez.avaj.launcher;

import io.github.aalvarez.avaj.launcher.aircraft.*;
import io.github.aalvarez.avaj.launcher.scenario.Scenario;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void
    main(String[] args) {
        Scenario scenario = ScenarioParser.parseScenario(Path.of(args[0]));
        IntStream.range(0, scenario.getSimulations()).forEach(_ -> {
            List<Flyable> observersCopy = new ArrayList<>(scenario.getWeatherTower().getObservers());
            if (!observersCopy.isEmpty()) {
                for (Flyable observer : observersCopy) {
                    if (observer instanceof Balloon balloon) {
                        balloon.updateConditions();
                    } else if (observer instanceof Helicopter helicopter) {
                        helicopter.updateConditions();
                    } else if (observer instanceof JetPlane jetPlane) {
                        jetPlane.updateConditions();
                    }
                }
                scenario.getWeatherTower().changeWeather();
            }
        });
    }
}