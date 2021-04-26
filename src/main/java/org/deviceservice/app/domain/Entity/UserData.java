package org.deviceservice.app.domain.Entity;

import lombok.Data;
import org.deviceservice.app.utility.OperationSystem;
import org.deviceservice.app.utility.RoleData;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_data")
public class UserData {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @NotEmpty
    @Column(name = "device_id")
    private Long deviceId;

    @NotNull
    @NotEmpty
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @NotEmpty
    @Column(name = "operation_system")
    private OperationSystem operationSystem;

    @NotNull
    @NotEmpty
    @Column(name = "role_data")
    private RoleData roleData;

}
