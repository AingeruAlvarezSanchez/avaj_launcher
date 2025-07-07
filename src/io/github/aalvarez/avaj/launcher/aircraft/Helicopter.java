package io.github.aalvarez.avaj.launcher.aircraft;

import io.github.aalvarez.avaj.launcher.utils.LoggerFactory;

import java.util.logging.Logger;

public class Helicopter extends Aircraft {
    private static final Logger log = LoggerFactory.getInstance().getLogger(Helicopter.class);

    public enum
    DefaultMessages {
        SUN("Sunny? Perfect for showing off those rotor skills!"),
        RAIN("Rain? More like nature’s car wash for helicopters!"),
        FOG("Fog? No worries, we hover like pros!"),
        SNOW("Snow? Just don’t eat the white stuff off the rotor…");

        private final String message;

        DefaultMessages(String message) {
            this.message = message;
        }
    }

    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void
    updateConditions() {
        WeatherProvider.Weathers currentWeather = WeatherProvider.Weathers.valueOf(weatherTower.getWeather(getCoordinates()));
        switch (currentWeather) {
            case SUN -> {
                log.info("Helicopter#" + name + "(" + id + ")" + DefaultMessages.SUN.message);
                getCoordinates().setLongitude(getCoordinates().getLongitude() + 10);
                getCoordinates().setHeight(getCoordinates().getHeight() + 2);
            }
            case RAIN -> {
                log.info("Helicopter#" + name + "(" + id + ")" + DefaultMessages.RAIN.message);
                getCoordinates().setLongitude(getCoordinates().getLongitude() + 5);
            }
            case FOG -> {
                log.info("Helicopter#" + name + "(" + id + ")" + DefaultMessages.FOG.message);
                getCoordinates().setLongitude(getCoordinates().getLongitude() + 1);
            }
            case SNOW -> {
                log.info("Helicopter#" + name + "(" + id + ")" + DefaultMessages.SNOW.message);
                getCoordinates().setHeight(getCoordinates().getHeight() - 12);
            }
        }
        if (getCoordinates().getHeight() > 100) {
            getCoordinates().setHeight(100);
        }

        if (getCoordinates().getHeight() <= 0) {
            logLandingMessage(Helicopter.class);
            weatherTower.unregister(this);
        }
    }
}
