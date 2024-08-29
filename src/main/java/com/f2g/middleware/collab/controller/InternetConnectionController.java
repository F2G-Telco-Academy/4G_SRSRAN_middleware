package com.f2g.middleware.collab.controller;

import com.f2g.middleware.collab.services.InternetConnectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection") // Or any suitable path
public class InternetConnectionController {

    private final InternetConnectionService internetConnectionService;

    public InternetConnectionController(InternetConnectionService internetConnectionService) {
        this.internetConnectionService = internetConnectionService;
    }


    @GetMapping("/check")
    public ResponseEntity<Boolean> checkInternetConnection() {
        boolean isAvailable = internetConnectionService.isInternetAvailable();
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
}