package beatmanagement.central.models.entities;

import beatmanagement.central.models.interfaces.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_locations")
public class BeatLocation extends Location {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private Long timestamp;
    @Column(nullable = false)
    private Long beatReportId;

    public BeatLocation(Double latitude, Double longitude, Long timestamp, Long beatReportId) {
        super(latitude, longitude);
        this.timestamp = timestamp;
        this.beatReportId = beatReportId;
    }

    public BeatLocation() {
    }

    public Long getBeatReportId() {
        return beatReportId;
    }

    public void setBeatReportId(Long beatReportId) {
        this.beatReportId = beatReportId;
    }

    @Override
    public String toString() {
        return "BeatLocation{" +
                "id=" + id +
                ", beatReportId=" + beatReportId +
                '}';
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
