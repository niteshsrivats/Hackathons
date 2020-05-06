package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.CitizenType;
import beatmanagement.central.models.interfaces.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "citizens")
public class Citizen extends User {
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    CitizenType citizenType;

    @ManyToOne(optional = true)
    Beat beat;

    public Citizen(@NotNull Double latitude, @NotNull Double longitude, @NotBlank String name, @NotNull CitizenType citizenType, Beat beat) {
        super(latitude, longitude, name);
        this.citizenType = citizenType;
        this.beat = beat;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "citizenType=" + citizenType +
                ", beat=" + beat +
                ", " + super.toString() +
                '}';
    }

    public CitizenType getCitizenType() {
        return citizenType;
    }

    public void setCitizenType(CitizenType citizenType) {
        this.citizenType = citizenType;
    }

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }
}
