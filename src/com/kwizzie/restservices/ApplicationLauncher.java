package com.kwizzie.restservices;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class ApplicationLauncher extends ResourceConfig{
	public ApplicationLauncher(){
        register(RequestContextFilter.class);
//        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(PlayerResource.class);
        register(LeaderBoardResource.class);
        register(PublicQuizRoomResource.class);
        register(PrivateQuizRoomResource.class);
        register(ImageUploadServlet.class);
	}
}
