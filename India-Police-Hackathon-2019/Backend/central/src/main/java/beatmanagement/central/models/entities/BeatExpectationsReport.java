package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.BeatExpectationReportType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_expectations_report")
public class BeatExpectationsReport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private Long phoneNumber;
    @Column(nullable = false)
//    @NotNull
    @Enumerated(EnumType.STRING)
    private BeatExpectationReportType beatExpectationReportType;
    @Column(nullable = true)
    @NotBlank
    private String description;

    public BeatExpectationsReport(String name, Long phoneNumber, BeatExpectationReportType beatExpectationReportType, String description) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.beatExpectationReportType = beatExpectationReportType;
        this.description = description;
    }

    public BeatExpectationsReport() {

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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BeatExpectationReportType getBeatExpectationReportType() {
        return beatExpectationReportType;
    }

    public void setBeatExpectationReportType(BeatExpectationReportType beatExpectationReportType) {
        this.beatExpectationReportType = beatExpectationReportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
