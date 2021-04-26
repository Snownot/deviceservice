package org.deviceservice.app.domain.dto;

import lombok.Data;
import org.deviceservice.app.utility.OperationSystem;
import org.deviceservice.app.utility.RoleData;

import java.util.UUID;

@Data
public class UserDataDto {

    private UUID id;

    private UUID deviceId;

    private UUID userId;

    private OperationSystem operationSystem;

    private RoleData roleData;

}
