package io.github.aalvarez.avaj.launcher.aircraft;

import io.github.aalvarez.avaj.launcher.utils.LoggerFactory;

import java.util.logging.Logger;

public class Balloon extends Aircraft {
    private static final Logger log = LoggerFactory.getInstance().getLogger(JetPlane.class);

    public enum
    DefaultMessages {
        SUN("It's a sunny day — don’t forget the sunscreen!"),
        RAIN("It's raining, but hey, free water refill!"),
        FOG("Visibility is low — hope we don’t meet any birds!"),
        SNOW("Snow is falling — hope we don’t pop from the chill!");

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
                log.info("Balloon#" + name + "(" + id + ")" + DefaultMessages.SUN.message);
                getCoordinates().setLatitude(getCoordinates().getLongitude() + 2);
                getCoordinates().setHeight(getCoordinates().getHeight() + 4);
            }
            case RAIN -> {
                log.info("Balloon#" + name + "(" + id + ")" + DefaultMessages.RAIN.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 5);
            }
            case FOG -> {
                log.info("Balloon#" + name + "(" + id + ")" + DefaultMessages.FOG.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 3);
            }
            case SNOW -> {
                log.info("Balloon#" + name + "(" + id + ")" + DefaultMessages.SNOW.message);
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
