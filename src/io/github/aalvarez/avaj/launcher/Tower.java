package io.github.aalvarez.avaj.launcher;

import io.github.aalvarez.avaj.launcher.exception.WeatherTowerException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getInstance().getLogger(Tower.class);

    public enum
    DefaultMessages {
        FLYABLE_REGISTERED_DEFAULT_MSG("Tower says: %s#%s(%d) registered to weather tower."),
        NON_VALID_AIRCRAFT_REGISTERED_DEFAULT_MSG("Tower says: Attempted to register a non-Aircraft object to weather tower.");

        private final String value;

        DefaultMessages(String value) {
            this.value = value;
        }
    }

    public static String
    createFlyableRegisteredMsg(String type, String name, long id) {
        return String.format(DefaultMessages.FLYABLE_REGISTERED_DEFAULT_MSG.value, type, name, id);
    }

    public void
    register(Flyable p_flyable) {
        if (p_flyable instanceof Aircraft aircraft) {
            if (observers.stream().anyMatch(f ->
                    ((Aircraft) f).name.equals(aircraft.name))
            ) throw new WeatherTowerException(IllegalArgumentException.class.getSimpleName());

            switch (aircraft) {
                case Balloon balloon ->
                        logger.info(createFlyableRegisteredMsg(balloon.getClass().getSimpleName(), balloon.name, balloon.id));
                case Helicopter helicopter ->
                        logger.info(createFlyableRegisteredMsg(helicopter.getClass().getSimpleName(), helicopter.name, helicopter.id));
                case JetPlane jetPlane ->
                        logger.info(createFlyableRegisteredMsg(jetPlane.getClass().getSimpleName(), jetPlane.name, jetPlane.id));
                default -> {
                    logger.severe(DefaultMessages.NON_VALID_AIRCRAFT_REGISTERED_DEFAULT_MSG.value + p_flyable.getClass().getName());
                    throw new WeatherTowerException(IllegalArgumentException.class.getSimpleName());
                }
            }
            observers.add(p_flyable);
        }
    }

    public void
    unregister(Flyable p_flyable) {
    }

    protected void
    conditionChanged() {
    }

    public List<Flyable>
    getObservers() {
        return observers;
    }
}