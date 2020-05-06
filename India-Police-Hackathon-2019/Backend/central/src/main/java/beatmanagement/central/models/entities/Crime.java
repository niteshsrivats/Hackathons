package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.CrimeType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity
@Table(name = "crimes")
public class Crime {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotNull
    private CrimeType crimeType;
    @Column(nullable = false)
    @NotBlank
    private String description;
    @Column(nullable = false)
    private Long beatId;
    @Column(nullable = false)
    private Long time;

    public Crime(@NotNull CrimeType crimeType, @NotBlank String description, Long beatId, Long time) {
        this.crimeType = crimeType;
        this.description = description;
        this.beatId = beatId;
        this.time = System.currentTimeMillis();
    }

    public Crime() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CrimeType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(CrimeType crimeType) {
        this.crimeType = crimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBeatId() {
        return beatId;
    }

    public void setBeatId(Long beatId) {
        this.beatId = beatId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
