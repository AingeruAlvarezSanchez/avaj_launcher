package io.github.aalvarez.avaj.launcher;

public class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();

    public enum
    Weathers {
        SUN("SUN"),
        RAIN("RAIN"),
        FOG("FOG"),
        SNOW("SNOW");

        private final String name;

        Weathers(String name) {
            this.name = name;
        }
    }

    private String[] weather = {
            Weathers.SUN.name,
            Weathers.RAIN.name,
            Weathers.FOG.name,
            Weathers.SNOW.name
    };

    private WeatherProvider() {
    }

    public static WeatherProvider
    getProvider() {
        return weatherProvider;
    }

    public String
    getCurrentWeather(Coordinates coordinates) {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();
        int result = (longitude + 1) * (latitude + 2) * (height + 3);
        return weather[(Math.abs(result ^ (longitude * latitude) ^ (height * 17)) % 4)];
    }
}
