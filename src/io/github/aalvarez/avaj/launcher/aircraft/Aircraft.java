package io.github.aalvarez.avaj.launcher.aircraft;

import io.github.aalvarez.avaj.launcher.utils.LoggerFactory;

import java.util.logging.Logger;

public class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected static final Logger logger = LoggerFactory.getInstance().getLogger(Aircraft.class);

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    @Override
    public void
    updateConditions() {
    }

    @Override
    public void
    registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
    }

    public Coordinates
    getCoordinates() {
        return coordinates;
    }

    protected <T extends Aircraft> void
    logLandingMessage(Class<T> clazz) {
        logger.info(String.format("%s#%s(%d): landing.", clazz.getSimpleName(), name, id));
    }
}
