package io.github.aalvarez.avaj.launcher.aircraft;

import io.github.aalvarez.avaj.launcher.utils.LoggerFactory;

import java.util.logging.Logger;

public class JetPlane extends Aircraft {
    private static final Logger log = LoggerFactory.getInstance().getLogger(JetPlane.class);

    public enum
    DefaultMessages {
        SUN("Sunny skies — time to break the sound barrier in style!"),
        RAIN("Rain? We’re already faster than the drops!"),
        FOG("Foggy runway — let’s pretend we’re in a spy movie!"),
        SNOW("Snow on the wings? Just another excuse to use more thrust!");

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
                log.info("JetPlane#" + name + "(" + id + ")" + DefaultMessages.SUN.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 10);
                getCoordinates().setHeight(getCoordinates().getHeight() + 2);
            }
            case RAIN -> {
                log.info("JetPlane#" + name + "(" + id + ")" + DefaultMessages.RAIN.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 5);
            }
            case FOG -> {
                log.info("JetPlane#" + name + "(" + id + ")" + DefaultMessages.FOG.message);
                getCoordinates().setLatitude(getCoordinates().getLatitude() + 1);
            }
            case SNOW -> {
                log.info("JetPlane#" + name + "(" + id + ")" + DefaultMessages.SNOW.message);
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
