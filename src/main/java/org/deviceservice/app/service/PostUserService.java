package org.deviceservice.app.service;

import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.domain.dto.UserDataDto;

public interface PostUserService {
    UserDataDto updateConnection(UpdateUserDataDto connectedUser);
}
