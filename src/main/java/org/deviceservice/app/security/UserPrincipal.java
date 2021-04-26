package org.deviceservice.app.security;

import lombok.Data;

@Data
public class UserPrincipal {

    private String username;

    public String getUsername() {
        return this.username;
    }


}

