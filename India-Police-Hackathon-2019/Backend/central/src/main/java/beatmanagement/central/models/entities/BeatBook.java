package beatmanagement.central.models.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_books")
public class BeatBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false)
    private Beat beat;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<BeatReport> beatReports;

    public BeatBook(Beat beat, Set<BeatReport> beatReports) {
        this.beat = beat;
        this.beatReports = beatReports;
    }

    public BeatBook() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beat getBeat() {
        return beat;
    }

    public void setBeat(Beat beat) {
        this.beat = beat;
    }

    public Set<BeatReport> getBeatReports() {
        return beatReports;
    }

    public void setBeatReports(Set<BeatReport> beatReports) {
        this.beatReports = beatReports;
    }
}
