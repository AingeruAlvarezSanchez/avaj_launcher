package io.github.aalvarez.avaj.launcher;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public abstract void
    updateConditions();

    public abstract void
    registerTower(WeatherTower weatherTower);
}