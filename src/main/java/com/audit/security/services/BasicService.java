package com.audit.security.services;

import java.util.Optional;

public interface BasicService<ENTITY> {
    public Optional<ENTITY> save(ENTITY entity);
}
