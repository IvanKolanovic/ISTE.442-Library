package com.ikolanovic.restapi.services;

import com.ikolanovic.restapi.exceptions.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public class BaseService {

    protected Supplier<RuntimeException> resourceNotFound(String str) {
        return () -> new ResourceNotFoundException(str);
    }
}
