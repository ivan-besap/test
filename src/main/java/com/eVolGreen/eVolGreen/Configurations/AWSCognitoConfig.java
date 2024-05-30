//package com.eVolGreen.eVolGreen.Configurations;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AWSCognitoConfig {
//
//    @Bean
//    public AWSCognitoIdentityProvider awsCognitoIdentityProvider() {
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
//        return AWSCognitoIdentityProviderClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withRegion(Regions.US_WEST_2)
//                .build();
//    }
//}
