package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.BeatIllegalActivitiesType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_illegal_activies_report")
public class BeatIllegalActivitiesReport {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private BeatIllegalActivitiesType beatIllegalActivitiesType;
    @Column(nullable = false)
    @NotBlank
    private String description;

    public BeatIllegalActivitiesReport(@NotNull BeatIllegalActivitiesType beatIllegalActivitiesType, @NotBlank String description) {
        this.beatIllegalActivitiesType = beatIllegalActivitiesType;
        this.description = description;
    }

    public BeatIllegalActivitiesReport() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeatIllegalActivitiesType getBeatIllegalActivitiesType() {
        return beatIllegalActivitiesType;
    }

    public void setBeatIllegalActivitiesType(BeatIllegalActivitiesType beatIllegalActivitiesType) {
        this.beatIllegalActivitiesType = beatIllegalActivitiesType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
