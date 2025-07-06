package io.github.aalvarez.avaj.launcher;

import java.util.logging.Logger;

public class Balloon extends Aircraft {
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

    public Balloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void
    updateConditions() {
        WeatherProvider.Weathers currentWeather = WeatherProvider.Weathers.valueOf(weatherTower.getWeather(coordinates));
        switch (currentWeather) {
            case SUN -> {
                log.info(DefaultMessages.SUN.message);
                getCoordinates().setLatitude(getCoordinates().getLongitude() + 2);
                getCoordinates().setHeight(getCoordinates().getHeight() + 4);
            }
            case RAIN -> {
                log.info(DefaultMessages.RAIN.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 5);
            }
            case FOG -> {
                log.info(DefaultMessages.FOG.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 3);
            }
            case SNOW -> {
                log.info(DefaultMessages.SNOW.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 15);
            }
        }
        if (getCoordinates().getHeight() > 100) {
            getCoordinates().setHeight(100);
        }

        if (getCoordinates().getHeight() <= 0) {
            logLandingMessage(Balloon.class);
            weatherTower.unregister(this);
        }
    }
}
