//package com.eVolGreen.eVolGreen.Configurations;
//
//import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
//import com.amazonaws.services.cognitoidp.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class CognitoService {
//
//    @Autowired
//    private AWSCognitoIdentityProvider cognitoClient;
//
//    public AuthResponse authenticateUser(String email, String password) {
//        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
//                .withUserPoolId("")
//                .withClientId("")
//                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
//                .withAuthParameters(getAuthParameters(email, password));
//
//        AdminInitiateAuthResult authResult = cognitoClient.adminInitiateAuth(authRequest);
//        return new AuthResponse(
//                authResult.getAuthenticationResult().getIdToken(),
//                authResult.getAuthenticationResult().getAccessToken(),
//                authResult.getAuthenticationResult().getRefreshToken()
//        );
//    }
//
//    public void registerUser(String firstName, String lastName, Integer rut, String email, Integer phone, String password) {
//        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
//                .withUserPoolId("")
//                .withUsername(email)
//                .withTemporaryPassword(password)
//                .withUserAttributes(
//                        new AttributeType().withName("email").withValue(email),
//                        new AttributeType().withName("phone_number").withValue(String.valueOf(phone)),
//                        new AttributeType().withName("custom:firstName").withValue(firstName),
//                        new AttributeType().withName("custom:lastName").withValue(lastName),
//                        new AttributeType().withName("custom:Rut").withValue(String.valueOf(rut))
//                )
//                .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);
//
//        AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(createUserRequest);
//    }
//
//    private Map<String, String> getAuthParameters(String email, String password) {
//        Map<String, String> authParameters = new HashMap<>();
//        authParameters.put("USERNAME", email);
//        authParameters.put("PASSWORD", password);
//        return authParameters;
//    }
//
//    public static class AuthResponse {
//        private String idToken;
//        private String accessToken;
//        private String refreshToken;
//
//        public AuthResponse(String idToken, String accessToken, String refreshToken) {
//            this.idToken = idToken;
//            this.accessToken = accessToken;
//            this.refreshToken = refreshToken;
//        }
//
//        // Getters and setters
//
//        public String getIdToken() {
//            return idToken;
//        }
//
//        public void setIdToken(String idToken) {
//            this.idToken = idToken;
//        }
//
//        public String getAccessToken() {
//            return accessToken;
//        }
//
//        public void setAccessToken(String accessToken) {
//            this.accessToken = accessToken;
//        }
//
//        public String getRefreshToken() {
//            return refreshToken;
//        }
//
//        public void setRefreshToken(String refreshToken) {
//            this.refreshToken = refreshToken;
//        }
//    }
//}
