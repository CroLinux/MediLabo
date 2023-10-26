package com.MediLabo.MSGateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("MsPatientApplication", r -> r.path("/ms-patient/**").uri("http://localhost:8081"))
				.route("MsFrontendApplication", r -> r.path("/ms-frontend/**").uri("http://localhost:8082"))
				.route("MsNoteApplication", r -> r.path("/ms-note/**").uri("http://localhost:8083"))
				.route("MsRiskApplication", r -> r.path("/ms-risk/**").uri("http://localhost:8084")).build();
	}

}
