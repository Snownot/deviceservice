package org.deviceservice.app.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Data
public class UserPrincipal implements UserDetails {

    private String username;
    private UUID id;
    private Boolean isExpired;
    private Boolean isAdmin;


    private Collection<? extends GrantedAuthority> authorities;
    //TODO hardcoded password for nonPassword auth
    @Override
    public String getPassword() {
        return "$2a$09$R.msfXFyveSxDkOzeqsF5u7NzZOd0ER9d2wlXitKAwwJKyda2dBpi";
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
