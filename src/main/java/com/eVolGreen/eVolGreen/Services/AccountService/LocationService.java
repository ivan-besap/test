package com.eVolGreen.eVolGreen.Services.AccountService;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationAccountCompanyDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;

import java.util.List;

public interface LocationService {

    List<LocationAccountCompanyDTO> getLocationsCompanyDTO();

    void saveLocation(Location location);

    Location findById(Long id);

    LocationAccountCompanyDTO getLocationCompanyDTO(Long id);

    void saveLocationCompany(Location location);

}
