package org.deviceservice.app.security;

import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.domain.dto.UserDataDto;
import org.deviceservice.app.service.PostUserService;
import org.deviceservice.app.utility.RoleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Resource(name = "postUserServiceImpl")
    private PostUserService postUserService;

    @Value("${auth.secret}")
    private String secret;

    private final JwtTokenConverter jwtTokenUtil;

    public JwtRequestFilter(JwtTokenConverter jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;

        UpdateUserDataDto connectedUser = null;
        UserDataDto credentialUser = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            connectedUser = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        if (connectedUser != null) {
            credentialUser = postUserService.updateConnection(connectedUser);
        }
        if (credentialUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserPrincipal userPrincipal = new UserPrincipal();
            userPrincipal.setUsername("unknown");
            userPrincipal.setId(connectedUser.getUserId());
            userPrincipal.setIsAdmin(connectedUser.getRoleData() == RoleData.BACK_OFFICE);


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userPrincipal, null, userPrincipal.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        chain.doFilter(request, response);
    }

}
