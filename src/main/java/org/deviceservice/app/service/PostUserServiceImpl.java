package org.deviceservice.app.service;

import lombok.RequiredArgsConstructor;
import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.domain.dto.UserDataDto;
import org.deviceservice.app.domain.entity.UserData;
import org.deviceservice.app.domain.repository.UserDataRepository;
import org.deviceservice.app.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostUserServiceImpl implements PostUserService {

    private final UserDataRepository userDataRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDataDto updateConnection(UpdateUserDataDto connectedUser) {
        try {

            UserData userData = userDataRepository.findByUserId(connectedUser.getUserId());

            if (userData == null) {
                throw new UserNotFoundException();
            }

            userData.setDeviceId(connectedUser.getDeviceId());
            userData.setRoleData(connectedUser.getRoleData());
            userData.setOperationSystem(connectedUser.getOperationSystem());
            userData.setUpdatedDate(LocalDate.now());

            return modelMapper.map(userDataRepository.saveAndFlush(userData), UserDataDto.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
