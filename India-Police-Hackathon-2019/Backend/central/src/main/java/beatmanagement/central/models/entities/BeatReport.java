package beatmanagement.central.models.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_reports")
public class BeatReport {

    @Id
    @GeneratedValue
    private Long id;
    //    @ManyToOne(optional = false)
//    BeatBook beatBook;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<BeatExpectationsReport> beatExpectationsReports;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Crime> crimes;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<BeatLawOrderIssuesReport> beatLawOrderIssuesReports;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<BeatIllegalActivitiesReport> beatIllegalActivitiesReports;

    @Column(nullable = true)
    private Long startTime;

    @Column(nullable = true)
    private Long endTime;

    @OneToOne(fetch = FetchType.EAGER)
    private Officer officer;
//
//    @OneToMany
//    private List<BeatLocation> beatLocations;


    public BeatReport(Set<BeatExpectationsReport> beatExpectationsReports, Set<Crime> crimes, Set<BeatLawOrderIssuesReport> beatLawOrderIssuesReports, Set<BeatIllegalActivitiesReport> beatIllegalActivitiesReports, Long startTime, Long endTime, Officer officer) {
        this.beatExpectationsReports = beatExpectationsReports;
        this.crimes = crimes;
        this.beatLawOrderIssuesReports = beatLawOrderIssuesReports;
        this.beatIllegalActivitiesReports = beatIllegalActivitiesReports;
        this.startTime = startTime;
        this.endTime = endTime;
        this.officer = officer;
    }

    public BeatReport() {

    }

    @Override
    public String toString() {
        return "BeatReport{" +
                "id=" + id +
                ", beatExpectationsReports=" + beatExpectationsReports +
                ", crimes=" + crimes +
                ", beatLawOrderIssuesReports=" + beatLawOrderIssuesReports +
                ", beatIllegalActivitiesReports=" + beatIllegalActivitiesReports +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", officer=" + officer +
                '}';
    }

    public Set<Crime> getCrimes() {
        return crimes;
    }

    public void setCrimes(Set<Crime> crimes) {
        this.crimes = crimes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<BeatExpectationsReport> getBeatExpectationsReports() {
        return beatExpectationsReports;
    }

    public void setBeatExpectationsReports(Set<BeatExpectationsReport> beatExpectationsReports) {
        this.beatExpectationsReports = beatExpectationsReports;
    }

    public Set<BeatLawOrderIssuesReport> getBeatLawOrderIssuesReports() {
        return beatLawOrderIssuesReports;
    }

    public void setBeatLawOrderIssuesReports(Set<BeatLawOrderIssuesReport> beatLawOrderIssuesReports) {
        this.beatLawOrderIssuesReports = beatLawOrderIssuesReports;
    }

    public Set<BeatIllegalActivitiesReport> getBeatIllegalActivitiesReports() {
        return beatIllegalActivitiesReports;
    }

    public void setBeatIllegalActivitiesReports(Set<BeatIllegalActivitiesReport> beatIllegalActivitiesReports) {
        this.beatIllegalActivitiesReports = beatIllegalActivitiesReports;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }
}
