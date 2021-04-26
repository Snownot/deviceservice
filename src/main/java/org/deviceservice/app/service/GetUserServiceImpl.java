package org.deviceservice.app.service;

import lombok.RequiredArgsConstructor;
import org.deviceservice.app.domain.dto.UserDataDto;
import org.deviceservice.app.domain.repository.UserDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUserServiceImpl implements GetUserService {

    private final UserDataRepository userDataRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<UserDataDto> getAllUserDataLog() {
        return userDataRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UserDataDto.class)).collect(Collectors.toList());
    }
}
