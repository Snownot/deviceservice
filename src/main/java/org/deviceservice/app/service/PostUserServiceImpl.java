package org.deviceservice.app.service;

import lombok.RequiredArgsConstructor;
import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.domain.entity.UserData;
import org.deviceservice.app.domain.repository.UserDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostUserServiceImpl implements PostUserService {

    private final UserDataRepository userDataRepository;

    @Override
    @Transactional
    public void updateConnection(UpdateUserDataDto connectedUser) {
        try {

            UserData userData = userDataRepository.findByUserId(connectedUser.getUserId());

            if (userData == null) throw new RuntimeException();

            userData.setDeviceId(connectedUser.getDeviceId());
            userData.setRoleData(connectedUser.getRoleData());
            userData.setOperationSystem(connectedUser.getOperationSystem());
            userData.setUpdatedDate(LocalDate.now());

            userDataRepository.saveAndFlush(userData);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
