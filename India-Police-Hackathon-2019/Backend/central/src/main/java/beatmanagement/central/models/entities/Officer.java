package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.OfficerType;
import beatmanagement.central.models.interfaces.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "officers")
public class Officer extends User {
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private OfficerType officerType;
    @ManyToOne(optional = true)
    private Beat beat;
    @Column(nullable = false)
    private Integer experience;
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Station station;

    public Officer(@NotNull Double latitude, @NotNull Double longitude, @NotBlank String name, @NotNull OfficerType officerType, Beat beat, Integer experience, @NotNull Station station) {
        super(latitude, longitude, name);
        this.officerType = officerType;
        this.beat = beat;
        this.experience = experience;
        this.station = station;
    }

    public Officer() {
        super();
    }

    @Override
    public String toString() {
        return "Officer{" +
                "officerType=" + officerType +
                ", beat=" + beat +
                ", experience=" + experience +
                ", station=" + station +
                ", " + super.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Officer)) return false;
        if (!super.equals(o)) return false;
        Officer officer = (Officer) o;
        return officerType == officer.officerType &&
                Objects.equals(beat, officer.beat) &&
                experience.equals(officer.experience) &&
                station.equals(officer.station);
    }

    public OfficerType getOfficerType() {
        return officerType;
    }

    public void setOfficerType(OfficerType officerType) {
        this.officerType = officerType;
    }

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
