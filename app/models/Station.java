package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
    public String title;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public float latitude;
    public float longitude;

    public Station(String title, float latitude, float longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}