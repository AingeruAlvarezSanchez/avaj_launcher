package io.github.aalvarez.avaj.launcher;

import java.nio.file.Path;
import java.util.stream.IntStream;

public class Main {
    public static void
    main(String[] args) {
        Scenario scenario = ScenarioParser.parseScenario(Path.of(args[0]));
        IntStream.range(0, scenario.getSimulations()).forEach(_ -> {
            scenario.getWeatherTower().getObservers().forEach(observer -> {
                if (observer instanceof Aircraft aircraft) {
                    System.out.println("Current weather: " + scenario.getWeatherTower().getWeather(aircraft.getCoordinates()));
                }
            });
        });
    }
}