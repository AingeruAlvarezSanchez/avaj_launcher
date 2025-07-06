package io.github.aalvarez.avaj.launcher;

public class WeatherTower extends Tower {
    public String
    getWeather(Coordinates p_coordinates) {
        return (WeatherProvider.getProvider().getCurrentWeather(p_coordinates));
    }

    public void
    changeWeather() {
        conditionChanged();
    }
}