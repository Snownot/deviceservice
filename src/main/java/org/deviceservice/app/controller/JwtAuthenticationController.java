package org.deviceservice.app.controller;


import jdk.net.SocketFlow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public ResponseEntity<?> createAuthenticationToken() {
        return ResponseEntity.ok(SocketFlow.Status.OK);
    }
}
