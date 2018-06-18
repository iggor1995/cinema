package com.epam.igor.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(WebSecurityConfig.class);
    }

    @Override
    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        EnumSet<DispatcherType> securityDispatcherTypes = super.getSecurityDispatcherTypes();
        securityDispatcherTypes.remove(DispatcherType.ASYNC);
        securityDispatcherTypes.remove(DispatcherType.ERROR);
        securityDispatcherTypes.add(DispatcherType.FORWARD);
        return securityDispatcherTypes;
    }


}
