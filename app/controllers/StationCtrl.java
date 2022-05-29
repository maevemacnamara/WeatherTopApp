package controllers;

import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);

        Reading latestReading = null;
        if (station.readings.size() > 0) {

            latestReading = station.readings.get(station.readings.size() - 1);
            //latestReading.calcFahrenheit();
        }
        render("station.html", station, latestReading);
    }

    public static void deleteReading(Long id, Long readingid) {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info("Removing reading " + reading.id);
        station.readings.remove(reading);
        station.save();
        reading.delete();
        Reading latestReading;
        if (station.readings.size() == 0) {
            latestReading = null;
        } else {
            latestReading = station.readings.get(station.readings.size() - 1);
        }
        render("station.html", station, latestReading);
    }

    public static void addReading(Long id, String code, double temp, double windSpeed, double windDirection, int pressure) {
        Reading reading = new Reading(code, temp, windSpeed, windDirection, pressure);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }
}
