package org.deviceservice.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.exception.UserFriendlyException;
import org.deviceservice.app.utility.OperationSystem;
import org.deviceservice.app.utility.RoleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class JwtTokenConverter {

    @Value("${auth.secret}")
    private String secret;

    public UpdateUserDataDto getUsernameFromToken(String token) {

        try {
            UpdateUserDataDto userDataDto = null;

            Claims body = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
            if (body != null) {
                userDataDto = new UpdateUserDataDto();
                userDataDto.setRoleData(RoleData.valueOf(body.get("roleData", String.class)));
                userDataDto.setOperationSystem(OperationSystem.valueOf(body.get("operationSystem", String.class)));
                userDataDto.setDeviceId(UUID.fromString(body.get("deviceId", String.class)));
                userDataDto.setUserId(UUID.fromString(body.get("userId", String.class)));
            }

            return userDataDto;
        } catch (SecurityException e) {
            throw new UserFriendlyException("token error");
        }
    }
}