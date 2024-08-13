package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.JobDTO;
import com.eVolGreen.eVolGreen.DTOS.JobRequestDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;
import com.eVolGreen.eVolGreen.Models.Job;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.JobService;
import com.eVolGreen.eVolGreen.Repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PermissionRepository permissionRepository;

    @PostMapping("/jobs")
    public ResponseEntity<Object> createRole(Authentication authentication,
                                             @RequestBody JobRequestDTO jobRequest) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        if (company == null) {
            return buildErrorResponse("POST /roles: No se encontró la empresa.", HttpStatus.NOT_FOUND);
        }

        String validationError = jobService.validateJobDTO(jobRequest.getJobDTO());
        if (validationError != null) {
            return buildErrorResponse("POST /roles: " + validationError, HttpStatus.BAD_REQUEST);
        }

        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(jobRequest.getPermissionIds()));
        Job job = jobService.convertToEntity(jobRequest.getJobDTO());
        job.setPermissions(permissions);
        jobService.saveJob(job);

        return new ResponseEntity<>("POST /roles: Rol creado correctamente.", HttpStatus.CREATED);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getJobs() {
        List<JobDTO> roles = jobService.getJobsDTO();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Object> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.getJobDTO(id);
        if (jobDTO == null) {
            return buildErrorResponse("GET /roles/{id}: No se encontró el rol con el id proporcionado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(jobDTO);
    }

    @PatchMapping("/jobs/activate/{id}")
    public ResponseEntity<Object> activateJob(@PathVariable Long id) {
        try {
            jobService.activateJob(id);
            return new ResponseEntity<>("PATCH /roles/activate/{id}: Rol activado correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PATCH /roles/activate/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return new ResponseEntity<>("DELETE /roles/delete/{id}: Rol eliminado correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("DELETE /roles/delete/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/jobs/update/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id,
                                             @RequestBody JobRequestDTO jobRequest) {

        String validationError = jobService.validateJobDTO(jobRequest.getJobDTO());
        if (validationError != null) {
            return buildErrorResponse("PUT /roles/update/{id}: " + validationError, HttpStatus.BAD_REQUEST);
        }

        Set<Permission> permissions = Set.copyOf(permissionRepository.findAllById(jobRequest.getPermissionIds()));

        try {
            Job updatedJob = jobService.updateJob(id, jobRequest.getJobDTO(), permissions);
            JobDTO updatedJobDTO = new JobDTO(updatedJob);
            return new ResponseEntity<>(updatedJobDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/update/{id}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




    @PutMapping("/jobs/addPermissions/{roleId}")
    public ResponseEntity<Object> addPermissions(@PathVariable Long jobId,
                                                 @RequestBody List<Long> permissionIds) {
        try {
            jobService.addPermissions(jobId, permissionIds);
            return new ResponseEntity<>("PUT /roles/addPermissions/{roleId}: Permisos agregados correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/addPermissions/{roleId}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/jobs/removePermissions/{roleId}")
    public ResponseEntity<Object> removePermissions(@PathVariable Long jobId,
                                                    @RequestBody List<Long> permissionIds) {
        try {
            jobService.removePermissions(jobId, permissionIds);
            return new ResponseEntity<>("PUT /roles/removePermissions/{roleId}: Permisos eliminados correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return buildErrorResponse("PUT /roles/removePermissions/{roleId}: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }
}
