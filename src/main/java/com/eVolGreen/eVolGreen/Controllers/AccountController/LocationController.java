package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationAccountCompanyDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.LocationRepository;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
import com.eVolGreen.eVolGreen.Services.AccountService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CompanyUserService companyUserService;

    @GetMapping("/locations")
    public List<LocationAccountCompanyDTO> getLocations() {
        return locationService.getLocationsCompanyDTO();
    }

    @GetMapping("/locations/{id}")
    public LocationAccountCompanyDTO getLocation(@PathVariable Long id) {
        return locationService.getLocationCompanyDTO(id);
    }

//    @PostMapping("/locations")
//    public ResponseEntity<Object> createLocation(Authentication authentication,
//                                                 @RequestBody Location location) {
//
//        // Print the incoming location details
//        System.out.println("Received location: " + location);
//
//        Company company = companyService.findByEmailCompany(authentication.getName());
//        System.out.println("Authenticated company: " + company);
//        String message;
//
//        if (company == null) {
//            System.out.println("No company found for the authenticated user.");
//            return ResponseEntity.status(403).body("No autorizado para realizar esta acción.");
//        }
//
//        if (location.getAddress().isBlank()) {
//            message = "La dirección no puede estar vacía.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//        if (location.getCity().isBlank()) {
//            message = "La ciudad no puede estar vacía.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//        if (location.getRegion().isBlank()) {
//            message = "La región no puede estar vacía.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//        if (location.getCountry().isBlank()) {
//            message = "El país no puede estar vacío.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//        if (location.getLatitude().isBlank()) {
//            message = "La latitud no puede estar vacía.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//        if (location.getLongitude().isBlank()) {
//            message = "La longitud no puede estar vacía.";
//            System.out.println(message);
//            return ResponseEntity.status(400).body(message);
//        }
//
//                        // Asignar la compañía a la nueva ubicación
//                Account account = company.getAccounts().stream().findFirst().orElse(null);
//                if (account == null) {
//                    System.out.println("No account found for the company.");
//                    return ResponseEntity.status(403).body("No se encontró una cuenta para esta compañía.");
//                }
//
//
//
//        Location newLocation = new Location(
//                location.getLatitude(),
//                location.getLongitude(),
//                location.getAddress(),
//                location.getCity(),
//                location.getRegion(),
//                location.getCountry()
//
//        );
//        locationRepository.save(newLocation);
//        location.setAccount(account);
//        message = "Ubicación creada correctamente";
//        return ResponseEntity.ok(message);
//    }
//
////        // Construir la dirección completa
////        String fullAddress = String.format("%s, %s, %s, %s", location.getAddress(), location.getCity(), location.getRegion(), location.getCountry());
////        System.out.println("Full address for geocoding: " + fullAddress);
////
////        try {
////            // Obtener resultados de geocodificación
////            GeocodingResult[] results = geocodingService.getGeocodingResults(fullAddress);
////            if (results.length > 0) {
////                GeocodingResult result = results[0];
////                location.setLatitude(String.valueOf(result.geometry.location.lat));
////                location.setLongitude(String.valueOf(result.geometry.location.lng));
////                System.out.println("Geocoding result: lat=" + result.geometry.location.lat + ", lng=" + result.geometry.location.lng);
////
////                // Asignar la compañía a la nueva ubicación
////                Account account = company.getAccounts().stream().findFirst().orElse(null);
////                if (account == null) {
////                    System.out.println("No account found for the company.");
////                    return ResponseEntity.status(403).body("No se encontró una cuenta para esta compañía.");
////                }
////
////                location.setAccount(account);
////
////                // Guardar la ubicación primero
////                Location savedLocation = locationRepository.save(location);
////                System.out.println("Location saved: " + savedLocation);
////
////                // Si hay estaciones de carga en la solicitud, configurarlas con esta ubicación
////                if (location.getChargingStations() != null) {
////                    for (ChargingStation chargingStation : location.getChargingStations()) {
////                        chargingStation.setLocation(savedLocation);
////                        // Puedes guardar cada estación de carga si es necesario
////                        // chargingStationService.save(chargingStation);
////                    }
////                }
////
////                return ResponseEntity.status(201).body(savedLocation);
////            } else {
////                message = "No se encontraron resultados para la dirección proporcionada.";
////                System.out.println(message);
////                return ResponseEntity.status(404).body(message);
////            }
////        } catch (Exception e) {
////            message = "Error al obtener coordenadas de la API de Google Maps: " + e.getMessage();
////            System.out.println(message);
////            return ResponseEntity.status(500).body(message);
////        }
//

    @PostMapping("/locations")
    public ResponseEntity<Object> createLocation(Authentication authentication,
                                                 @RequestBody LocationAccountCompanyDTO location) {
        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String message;

        if (company == null) {
            System.out.println("No company found for the authenticated user.");
            return ResponseEntity.status(403).body("No autorizado para realizar esta acción.");
        }

        if (location.getDireccion().isBlank()) {
            message = "La dirección no puede estar vacía.";
            System.out.println(message);
            return ResponseEntity.status(400).body(message);
        }

        Location newLocation = new Location(location.getDireccion());
        newLocation.setCuentaCompania(company.getCuentaCompañia().stream().findFirst().orElse(null));
        locationRepository.save(newLocation);

        message = "Ubicación creada correctamente";
        return ResponseEntity.ok(message);
    }

}