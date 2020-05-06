package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private Long requests = 0L;

    private Long executionTime = null;

    private Long memoryConsumption = null;

    private Long successes = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequests() {
        return requests;
    }

    public void setRequests(Long requests) {
        this.requests = requests;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getMemoryConsumption() {
        return memoryConsumption;
    }

    public void setMemoryConsumption(Long memoryConsumption) {
        this.memoryConsumption = memoryConsumption;
    }

    public Long getSuccesses() {
        return successes;
    }

    public void setSuccesses(Long successes) {
        this.successes = successes;
    }

    public void updateRequests(int requests) {
        this.requests += requests;
    }

    public void updateSuccesses(int successes) {
        this.successes += successes;
    }

    public void updateExecutionTime(long executionTime) {
        if (this.executionTime == null) {
            this.executionTime = executionTime;
        } else {
            this.executionTime += executionTime;
        }
    }

    public void updateMemoryConsumption(long memoryConsumption) {
        if (this.memoryConsumption == null) {
            this.memoryConsumption = memoryConsumption;
        } else {
            this.memoryConsumption += memoryConsumption;
        }
    }
}
