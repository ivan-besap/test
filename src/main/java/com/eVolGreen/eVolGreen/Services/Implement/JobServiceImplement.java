package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.JobDTO;
import com.eVolGreen.eVolGreen.Models.Job;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Repositories.CompanyRepository;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import com.eVolGreen.eVolGreen.Repositories.JobRepository;
import com.eVolGreen.eVolGreen.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImplement implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public void saveJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobDTO(Long id) {
        return jobRepository.findById(id).map(JobDTO::new).orElse(null);
    }

    @Override
    public List<JobDTO> getJobsDTO() {
        return jobRepository.findAll()
                .stream()
                .map(JobDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Job> findByName(String name) {
        return jobRepository.findByName(name);
    }

    @Override
    public Job convertToEntity(JobDTO jobDTO) {
        Job job = new Job();
        job.setName(jobDTO.getName());
        return job;
    }

    @Override
    public String validateJobDTO(JobDTO jobDTO) {
        if (jobDTO.getName() == null || jobDTO.getName().isBlank()) {
            return "El nombre del rol no puede estar vacío.";
        }
        return null;
    }


    @Override
    @Transactional
    public void activateJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        job.setIsActive(true);
        jobRepository.save(job);
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        if (!job.getIsActive()) {
            throw new RuntimeException("El rol ya está eliminado.");
        }

        job.setIsActive(false);
        jobRepository.save(job);
    }

    @Override
    @Transactional
    public Job updateJob(Long id, JobDTO jobDTO, Set<Permission> newPermissions) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));

        job.setName(jobDTO.getName());

        // Actualizar los permisos
        job.getPermissions().clear();
        job.getPermissions().addAll(newPermissions);

        job.setIsActive(true);

        return jobRepository.save(job);
    }

    @Override
    @Transactional
    public void addPermissions(Long JobId, List<Long> permissionIds) {
        Job job = jobRepository.findById(JobId)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
        job.getPermissions().addAll(permissions);
        jobRepository.save(job);
    }

    @Override
    @Transactional
    public void removePermissions(Long JobId, List<Long> permissionIds) {
        Job job = jobRepository.findById(JobId)
                .orElseThrow(() -> new RuntimeException("Role no encontrado."));
        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(permissionIds));
        job.getPermissions().removeAll(permissions);
        jobRepository.save(job);
    }
}
