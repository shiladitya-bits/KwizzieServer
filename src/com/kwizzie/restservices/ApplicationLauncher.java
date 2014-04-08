package com.kwizzie.restservices;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationLauncher extends ResourceConfig{
	public ApplicationLauncher(){
        register(RequestContextFilter.class);
        register(ObjectMapper.class);
        register(PlayerResource.class);
	}
}
