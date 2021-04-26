package org.deviceservice.app.domain.dto;

import lombok.Data;
import org.deviceservice.app.utility.OperationSystem;
import org.deviceservice.app.utility.RoleData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UpdateUserDataDto {

    @NotNull
    @NotEmpty
    private UUID deviceId;

    @NotNull
    @NotEmpty
    private UUID userId;

    @NotNull
    @NotEmpty
    private OperationSystem operationSystem;

    @NotNull
    @NotEmpty
    private RoleData roleData;
}
