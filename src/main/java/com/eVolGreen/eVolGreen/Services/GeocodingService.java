package com.eVolGreen.eVolGreen.Services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeocodingService {
    private final GeoApiContext geoApiContext;

    public GeocodingService(@Value("${google.maps.api.key}") String apiKey) {
        this.geoApiContext = new GeoApiContext.Builder().apiKey(apiKey).build();
    }

    public GeocodingResult[] getGeocodingResults(String address) throws Exception {
        return GeocodingApi.geocode(geoApiContext, address).await();
    }
}
