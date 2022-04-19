package com.parking.parkingspaces.config.eureka;

import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class EurekaClient {
    private final DiscoveryClient discoveryClient;

    public URI getURI(String serviceName) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
        if (serviceInstances != null && !serviceInstances.isEmpty()) {
            return serviceInstances.get(0).getUri();
        }
        return null;
    }
}
