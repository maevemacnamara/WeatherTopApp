package controllers;

import java.util.List;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

public class Dashboard extends Controller {
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Station> stations = member.stations;
        //List<Station> stations = Station.findAll();
        render("dashboard.html", member, stations);
    }

    public static void deleteStation(Long id, Long memberid) {
        Member member = Member.findById(memberid);
        Station station = Station.findById(id);
        member.stations.remove(station);
        member.save();
        station.delete();
        Logger.info("Deleting " + station.title);
        redirect("/dashboard");
    }

    public static void addStation(String title, float latitude, float longitude) {
        Member member = Accounts.getLoggedInMember();
        Station station = new Station(title, latitude, longitude);
        member.stations.add(station);
        station.save();
        Logger.info("Adding a new station called " + title);
        redirect("/dashboard");
    }
}
