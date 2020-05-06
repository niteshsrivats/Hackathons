package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.CriminalType;
import beatmanagement.central.models.interfaces.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "criminals")
public class Criminal extends User {
    @Column(nullable = false)
    @NotNull
    private CriminalType criminalType;
    //    @ManyToOne(optional = false)
    private Long beatId;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Crime> crimes;

    public Criminal(@NotNull Double latitude, @NotNull Double longitude, @NotBlank String name, @NotNull CriminalType criminalType, Long beatId, Set<Crime> crimes) {
        super(latitude, longitude, name);
        this.criminalType = criminalType;
        this.beatId = beatId;
        this.crimes = crimes;
    }

    public Criminal() {
    }

    public CriminalType getCriminalType() {
        return criminalType;
    }

    public void setCriminalType(CriminalType criminalType) {
        this.criminalType = criminalType;
    }

    public Long getBeatId() {
        return beatId;
    }

    public void setBeatId(Long beatId) {
        this.beatId = beatId;
    }

    public Set<Crime> getCrimes() {
        return crimes;
    }

    public void setCrimes(Set<Crime> crimes) {
        this.crimes = crimes;
    }
}
