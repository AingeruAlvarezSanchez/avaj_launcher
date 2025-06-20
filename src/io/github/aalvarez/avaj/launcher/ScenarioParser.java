package io.github.aalvarez.avaj.launcher;

import io.github.aalvarez.avaj.launcher.exception.ScenarioFileException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ScenarioParser {
    private static final Logger logger = LoggerFactory.getInstance().getLogger(ScenarioParser.class);

    public static Scenario
    parseScenario(Path path) {
        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        WeatherTower weatherTower = new WeatherTower();
        logger.log(Level.INFO, "Parsing scenario file: {0}", path.toAbsolutePath());

        int simulations;
        try (var fileLines = Files.lines(path).map(String::trim).filter(line -> !line.isEmpty())) {
            List<String> lines = fileLines.toList();
            if (lines.isEmpty()) throw new IllegalArgumentException();
            simulations = Integer.parseInt(lines.getFirst());
            if (simulations < 0) throw new IllegalArgumentException();

            lines.stream().skip(1).map(line -> {
                var words = line.split("\\s+");
                if (words.length != 5) throw new IllegalArgumentException();

                if (IntStream.rangeClosed(2, 4).anyMatch(i -> {
                    try {
                        return Integer.parseInt(words[i]) <= 0 || (i == 2 && Integer.parseInt(words[i]) > 100);
                    } catch (NumberFormatException e) {
                        return true;
                    }
                })) throw new IllegalArgumentException();

                return words;
            }).forEach(word -> weatherTower.register(aircraftFactory.newAircraft(
                            word[0],
                            word[1],
                            new Coordinates(Integer.parseInt(word[2]),
                                    Integer.parseInt(word[3]),
                                    Integer.parseInt(word[4]))
                    ))
            );
        } catch (Exception e) {
            throw new ScenarioFileException(e, e.getClass().getSimpleName());
        }

        logger.log(Level.INFO, "Scenario file parsed successfully: {0}", path.toAbsolutePath());
        return new Scenario(simulations, weatherTower);
    }
}
