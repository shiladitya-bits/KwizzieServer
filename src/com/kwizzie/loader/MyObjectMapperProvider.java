package com.kwizzie.loader;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {
 
    final ObjectMapper defaultObjectMapper;
    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }
 
    @Override
    public ObjectMapper getContext(Class<?> type) {
            return defaultObjectMapper;
    }
 
    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        return result;
    }
 
    // ...
}
