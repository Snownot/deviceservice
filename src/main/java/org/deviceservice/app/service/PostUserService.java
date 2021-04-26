package org.deviceservice.app.service;

import org.deviceservice.app.domain.dto.UpdateUserDataDto;

public interface PostUserService {
    void updateConnection(UpdateUserDataDto connectedUser);
}
