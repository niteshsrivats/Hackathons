package com.remote.exec.central.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "submission_stats")
public class SubmissionStats {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(updatable = false)
    private String input;

    @Column(updatable = false)
    private String output;

    @Column(updatable = false)
    private Boolean success;

    @Column(updatable = false)
    private Long runTime = null;

    @Column(updatable = false)
    private Long memory = null;

    @Column(updatable = false)
    private Long time = System.currentTimeMillis();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    public Long getMemory() {
        return memory;
    }

    public void setMemory(Long memory) {
        this.memory = memory;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SubmissionStats{" +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", success=" + success +
                ", runTime=" + runTime +
                ", memory=" + memory +
                ", time=" + time +
                '}';
    }
}
