package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Job;

import java.util.Set;
import java.util.stream.Collectors;

public class JobDTO {

    private Long id;
    private String name;
    private Set<String> permissions;
    private boolean isActive;

    public JobDTO() { }

    public JobDTO(Job job) {
        this.id = job.getId();
        this.name = job.getName();
        this.permissions = job.getPermissions().stream()
                .map(permission -> permission.getName())
                .collect(Collectors.toSet());
        this.isActive = job.getIsActive();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public boolean isActive() {
        return isActive;
    }
}
