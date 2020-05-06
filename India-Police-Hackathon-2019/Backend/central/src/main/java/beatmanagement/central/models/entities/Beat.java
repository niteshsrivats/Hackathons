package beatmanagement.central.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beats")
public class Beat {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
//    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Size(min = 8, max = 8)
    private List<Double> location;
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Station station;
//    @OneToMany(mappedBy = "beat")
//    Set<BeatHotspot> beatHotspots;

    public Beat(List<Double> location, Station station) {
        this.location = location;
        this.station = station;
    }

    public Beat() {
    }

    @Override
    public String toString() {
        return "Beat{" +
                "id=" + id +
                ", location=" + location +
                ", station=" + station +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
