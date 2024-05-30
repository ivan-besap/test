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
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAZI2LDAZ3LBZWOI2F", "YTfbHmN2qtgv465ssxLyU9GfM8SvBN/1rcrShTu1");
//        return AWSCognitoIdentityProviderClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withRegion(Regions.US_WEST_2)
//                .build();
//    }
//}
