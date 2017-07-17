package rf.digworld.headhands.test.common;

import java.util.UUID;

import rf.digworld.headhands.data.model.WeatherResponce;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }
    public static Double randomTemp() {
        return Math.random()*20;
    }
    public static int  randomHudmity() {
        return (int)Math.random()*100;
    }
    public static int  randomPressure() {
        return (int)Math.random()*1000;
    }

    public static WeatherResponce makeWeather() {
        return new WeatherResponce(randomTemp(),randomPressure(),randomHudmity() );
    }



}