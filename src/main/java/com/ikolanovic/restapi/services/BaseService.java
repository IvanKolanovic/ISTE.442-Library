package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.exceptions.ResourceNotFoundException;
import com.ikolanovic.restapi.models.User;
import com.ikolanovic.restapi.models.security.MyUserDetails;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.function.Supplier;

@Getter
public class BaseService {

    protected Supplier<RuntimeException> resourceNotFound(String str) {
        return () -> new ResourceNotFoundException(str);
    }

    protected Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((MyUserDetails) authentication.getPrincipal()).getUser();
    }
}
