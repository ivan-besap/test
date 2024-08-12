package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Models.Permission;

public class PermissionDTO {

    private Long id;
    private String name;
    private String description;

    public PermissionDTO() { }

    public PermissionDTO (Permission permission) {
        id = permission.getId();
        name = permission.getName();
        description = permission.getDescription();
    }

    public Long getId() { return id;  }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
