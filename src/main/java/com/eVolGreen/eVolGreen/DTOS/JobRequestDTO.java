package com.eVolGreen.eVolGreen.DTOS;

import java.util.List;

public class JobRequestDTO {

    private JobDTO jobDTO;
    private List<Long> permissionIds;

    public JobDTO getJobDTO() {
        return jobDTO;
    }

    public void setJobDTO(JobDTO jobDTO) {
        this.jobDTO = jobDTO;
    }

    public List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
