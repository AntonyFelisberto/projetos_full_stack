package com.projetooficina.sistemaoficinaback.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SnsConfiguration {

    @Value("${cloud.aws.credentials.acess-key}")
    private String acessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    @Primary
    public AmazonSNSClient amazonSNSClient(){
        BasicAWSCredentials credentials = new BasicAWSCredentials(acessKey,secretKey);
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion("us-east-1")
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
