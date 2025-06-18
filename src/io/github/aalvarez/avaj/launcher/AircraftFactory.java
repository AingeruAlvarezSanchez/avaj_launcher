package io.github.aalvarez.avaj.launcher;

import java.util.concurrent.atomic.AtomicInteger;

public class AircraftFactory {
    public static final AircraftFactory factory = new AircraftFactory();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    private AircraftFactory() {
    }

    public static AircraftFactory
    getInstance() {
        return factory;
    }

    public enum
    AircraftType {
        BALLOON,
        HELICOPTER,
        JETPLANE
    }

    public Flyable
    newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws IllegalArgumentException {
        return null;
    }
}
