package org.deviceservice.app.domain.repository;

import org.deviceservice.app.domain.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    UserData findByUserId(UUID id);

}
