package com.MediLabo.MSFrontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig 
{
	// Configuration Bean for sending the Authentication's values to the other microservices
   @Bean
   BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()
{
      return  new BasicAuthRequestInterceptor("user", "user");
   }
}
