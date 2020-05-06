package beatmanagement.central.models.entities;

import beatmanagement.central.models.constants.BeatLawOrderIssueType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "beat_law_order_issues_report")
public class BeatLawOrderIssuesReport {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private BeatLawOrderIssueType beatLawOrderIssueType;
    @Column(nullable = false)
    @NotBlank
    private String description;

    public BeatLawOrderIssuesReport(@NotNull BeatLawOrderIssueType beatLawOrderIssueType, @NotBlank String description) {
        this.beatLawOrderIssueType = beatLawOrderIssueType;
        this.description = description;
    }

    public BeatLawOrderIssuesReport() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BeatLawOrderIssueType getBeatLawOrderIssueType() {
        return beatLawOrderIssueType;
    }

    public void setBeatLawOrderIssueType(BeatLawOrderIssueType beatLawOrderIssueType) {
        this.beatLawOrderIssueType = beatLawOrderIssueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
