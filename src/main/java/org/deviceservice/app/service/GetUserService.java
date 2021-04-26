package org.deviceservice.app.service;

import org.deviceservice.app.domain.dto.UserDataDto;

import java.util.List;

public interface GetUserService {
    List<UserDataDto> getAllUserDataLog();
}
