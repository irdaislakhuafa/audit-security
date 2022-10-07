package com.audit.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize(value = "hasAnyAuthority('auditor')")
@RequestMapping(value = { "/hello" })
public class HelloController {
    @RequestMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok().body("Hello World");
    }
}
