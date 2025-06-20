package io.github.aalvarez.avaj.launcher;

import java.util.concurrent.atomic.AtomicInteger;

public class AircraftFactory {
    private static final AircraftFactory factory = new AircraftFactory();
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
        AircraftType type = AircraftType.valueOf(p_type.toUpperCase());
        return switch (type) {
            case BALLOON -> new Balloon(idCounter.incrementAndGet(), p_name, p_coordinates);
            case HELICOPTER -> new Helicopter(idCounter.incrementAndGet(), p_name, p_coordinates);
            case JETPLANE -> new JetPlane(idCounter.incrementAndGet(), p_name, p_coordinates);
        };
    }
}
