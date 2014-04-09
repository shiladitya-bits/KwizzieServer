package com.kwizzie.restservices;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationLauncher extends ResourceConfig{
	public ApplicationLauncher(){
        register(RequestContextFilter.class);
        register(PlayerResource.class);
        register(LeaderBoardResource.class);
        register(PublicQuizRoomResource.class);
        register(PrivateQuizRoomResource.class);
	}
}
