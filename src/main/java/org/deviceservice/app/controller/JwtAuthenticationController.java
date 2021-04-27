package org.deviceservice.app.controller;

import jdk.net.SocketFlow;
import org.deviceservice.app.domain.dto.UserDataDto;
import org.deviceservice.app.service.GetUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Resource(name = "getUserServiceImpl")
    private GetUserService getUserService;

    @RequestMapping(value = "/connect", method = RequestMethod.HEAD)
    public ResponseEntity<?> connect() {
        return ResponseEntity.ok(SocketFlow.Status.OK);
    }

    @RequestMapping(value = "/getAllDataUser", method = RequestMethod.GET)
    public ResponseEntity<List<UserDataDto>> getAllDataUser() {
        List<UserDataDto> result = getUserService.getAllUserDataLog();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
