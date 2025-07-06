package io.github.aalvarez.avaj.launcher;

import java.util.logging.Logger;

public class JetPlane extends Aircraft {
    private static final Logger log = LoggerFactory.getInstance().getLogger(JetPlane.class);

    public enum
    DefaultMessages {
        SUN("It's a sunny day!"),
        RAIN("It's raining, but we can still fly."),
        FOG("Visibility is low, be cautious!"),
        SNOW("Snow is falling, we need to be careful with the weight.");

        private final String message;

        DefaultMessages(String message) {
            this.message = message;
        }
    }

    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void
    updateConditions() {
        WeatherProvider.Weathers currentWeather = WeatherProvider.Weathers.valueOf(weatherTower.getWeather(getCoordinates()));
        switch (currentWeather) {
            case SUN -> {
                log.info(DefaultMessages.SUN.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 10);
                getCoordinates().setHeight(getCoordinates().getHeight() + 2);
            }
            case RAIN -> {
                log.info(DefaultMessages.RAIN.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 5);
            }
            case FOG -> {
                log.info(DefaultMessages.FOG.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 1);
            }
            case SNOW -> {
                log.info(DefaultMessages.SNOW.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 7);
            }
        }
        if (getCoordinates().getHeight() > 100) {
            getCoordinates().setHeight(100);
        }

        if (getCoordinates().getHeight() <= 0) {
            logLandingMessage(JetPlane.class);
            weatherTower.unregister(this);
        }
    }
}
