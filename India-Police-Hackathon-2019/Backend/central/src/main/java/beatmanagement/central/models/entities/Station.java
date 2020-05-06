package beatmanagement.central.models.entities;

import beatmanagement.central.models.interfaces.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "stations")
public class Station extends Location {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String name;

    public Station(@NotNull Double latitude, @NotNull Double longitude, @NotBlank String name) {
        super(latitude, longitude);
        this.name = name;
    }

    public Station() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
