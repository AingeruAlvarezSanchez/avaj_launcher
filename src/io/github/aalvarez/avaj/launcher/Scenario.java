package io.github.aalvarez.avaj.launcher;

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

    public void
    setSimulations(int simulations) {
        this.simulations = simulations;
    }

    public WeatherTower
    getWeatherTower() {
        return weatherTower;
    }

    public void
    setWeatherTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }
}
