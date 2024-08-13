package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.JobDTO;
import com.eVolGreen.eVolGreen.Models.Job;
import com.eVolGreen.eVolGreen.Models.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface JobService {

    void saveJob(Job job);

    List<JobDTO> getJobsDTO();

    JobDTO getJobDTO(Long id);

    Job findById(Long id);

    Optional<Job> findByName(String name);

    Job convertToEntity(JobDTO jobDTO);

    String validateJobDTO(JobDTO jobDTO);

    void activateJob(Long id);

    void deleteJob(Long id);

    @Transactional
    Job updateJob(Long id, JobDTO jobDTO, Set<Permission> newPermissions);

    void addPermissions(Long JobId, List<Long> permissionIds);

    void removePermissions(Long JobId, List<Long> permissionIds);
}
