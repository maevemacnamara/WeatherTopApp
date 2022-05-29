package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.util.Objects;

import static java.lang.Math.pow;

@Entity
public class Reading extends Model {
    public String code;
    public String condition;
    public double temp;
    private double fahrenheit;
    public double windSpeed;
    public double beaufort;
    public double windDirection;
    public String compassDirection;
    public double windChill;
    public int pressure;


    public Reading(String code, double temp, double windSpeed, double windDirection, int pressure) {
        initialiseReading(code, "", temp, windSpeed, 0, windDirection, "", 0, pressure);
    }

    public Reading(String code, String condition, double temp, double windSpeed, int beaufort,
                   double windDirection, String compassDirection, double windChill, int pressure) {
        initialiseReading(code, condition, temp, windSpeed, beaufort,
                windDirection, compassDirection, windChill, pressure);

    }

    private void initialiseReading(String code, String condition, double temp, double windSpeed, int beaufort,
                                   double windDirection, String compassDirection, double windChill, int pressure) {
        this.code = code;
        this.condition = condition;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.beaufort = beaufort;
        this.windDirection = windDirection;
        this.compassDirection = compassDirection;
        this.windChill = windChill;
        this.pressure = pressure;
    }

    public String getCondition() {
        if (Objects.equals(code, "100")) {
            condition = "Clear";
        }
        if (Objects.equals(code, "200")) {
            condition = "Partial Clouds";
        }
        if (Objects.equals(code, "300")) {
            condition = "Cloudy";
        }
        if (Objects.equals(code, "400")) {
            condition = "Light Showers";
        }
        if (Objects.equals(code, "500")) {
            condition = "Heavy Showers";
        }
        if (Objects.equals(code, "600")) {
            condition = "Rain";
        }
        if (Objects.equals(code, "700")) {
            condition = "Snow";
        }
        if (Objects.equals(code, "800")) {
            condition = "Thunder";
        }
        System.out.println(condition);
        return condition;
    }

    public double getFahrenheit() {
        return temp * 9 / 5 + 32;
    }

    public double getBeaufort() {
        if (windSpeed > 0 && windSpeed < 2) beaufort = 0;
        if (windSpeed > 1 && windSpeed < 6) beaufort = 1;
        if (windSpeed > 5 && windSpeed < 12) beaufort = 2;
        if (windSpeed > 11 && windSpeed < 20) beaufort = 3;
        if (windSpeed > 19 && windSpeed < 29) beaufort = 4;
        if (windSpeed > 28 && windSpeed < 39) beaufort = 5;
        if (windSpeed > 38 && windSpeed < 50) beaufort = 6;
        if (windSpeed > 49 && windSpeed < 62) beaufort = 7;
        if (windSpeed > 61 && windSpeed < 75) beaufort = 8;
        if (windSpeed > 75 && windSpeed < 89) beaufort = 9;
        if (windSpeed > 88 && windSpeed < 103) beaufort = 10;
        if (windSpeed > 102 && windSpeed < 118) beaufort = 11;
        return beaufort;
    }


    public String getCompassDirection() {
        // direction will be east variation
        if (windDirection > 11.00 && windDirection < 101.25) {
            if (windDirection > 11.24 && windDirection < 33.76) compassDirection = "NNE";
            if (windDirection > 33.74 && windDirection < 56.26) compassDirection = "NE";
            if (windDirection > 56.24 && windDirection < 78.76) compassDirection = "ENE";
            if (windDirection > 78.74 && windDirection < 101.26) compassDirection = "E";
        }
        //direction will be of south variation
        if (windDirection > 101.24 && windDirection < 191.25) {
            if (windDirection > 101.24 && windDirection < 123.76) compassDirection = "ESE";
            if (windDirection > 123.74 && windDirection < 146.26) compassDirection = "SE";
            if (windDirection > 146.24 && windDirection < 168.76) compassDirection = "SSE";
            if (windDirection > 168.74 && windDirection < 191.26) compassDirection = "S";
        }
        //direction will be of west variation
        if (windDirection > 190.24 && windDirection < 348.76) {
            if (windDirection > 191.24 && windDirection < 213.76) compassDirection = "SSW";
            if (windDirection > 213.74 && windDirection < 236.26) compassDirection = "SW";
            if (windDirection > 236.24 && windDirection < 258.76) compassDirection = "WSW";
            if (windDirection > 258.74 && windDirection < 281.26) compassDirection = "W";
            if (windDirection > 281.24 && windDirection < 303.76) compassDirection = "WNW";
            if (windDirection > 303.74 && windDirection < 326.26) compassDirection = "NW";
            if (windDirection > 326.24 && windDirection < 348.76) compassDirection = "NNW";
        } else { //compassDirection must be north
            compassDirection = "N";
        }
        return compassDirection;
    }

    private double getWindChill() {
        windChill = 13.12 + (0.6215 * temp) - 11.37 * (pow(windSpeed, 0.16)) + 0.3965 * temp * (pow(windSpeed, 0.16));
        return windChill;
    }
}

