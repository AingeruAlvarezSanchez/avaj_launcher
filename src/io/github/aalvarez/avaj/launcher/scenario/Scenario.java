package io.github.aalvarez.avaj.launcher.scenario;

import io.github.aalvarez.avaj.launcher.aircraft.WeatherTower;

public class Scenario {
    private int simulations;
    private WeatherTower weatherTower;

    public Scenario(int simulations, WeatherTower weatherTower) {
        this.simulations = simulations;
        this.weatherTower = weatherTower;
    }

    public int
    getSimulations() {
        return simulations;
    }

    public WeatherTower
    getWeatherTower() {
        return weatherTower;
    }
}
