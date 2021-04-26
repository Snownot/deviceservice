package org.deviceservice.app.domain.entity;

import lombok.Data;
import org.deviceservice.app.utility.OperationSystem;
import org.deviceservice.app.utility.RoleData;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @Column(name = "device_id")
    private UUID deviceId;

    @NotNull
    @Column(name = "user_id")
    private UUID userId;

    @NotNull
    @Column(name = "operation_system")
    private OperationSystem operationSystem;

    @NotNull
    @Column(name = "role_data")
    private RoleData roleData;

    @NotNull
    @Column(name = "created_data")
    private LocalDate createdDate;

    @NotNull
    @Column(name = "updated_data")
    private LocalDate updatedDate;

}
