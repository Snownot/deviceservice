package org.deviceservice.app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.deviceservice.app.domain.dto.UpdateUserDataDto;
import org.deviceservice.app.service.PostUserService;
import org.deviceservice.app.utility.RoleData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.List;
import java.util.stream.Collectors;

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

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            connectedUser = jwtTokenUtil.getUsernameFromToken(jwtToken);
        }

        if (connectedUser != null) {
            postUserService.updateConnection(connectedUser);
        }
        if (connectedUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(jwtToken);

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
