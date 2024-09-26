package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Configuration;

import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ClientCoreProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OcppConfig {

    @Bean
    public ClientCoreProfile clientCoreProfile( ClientCoreEventHandler clientCoreEventHandler) {
        // Aquí puedes inicializar cualquier dependencia si es necesario
        return new ClientCoreProfile(clientCoreEventHandler);
    }
}
