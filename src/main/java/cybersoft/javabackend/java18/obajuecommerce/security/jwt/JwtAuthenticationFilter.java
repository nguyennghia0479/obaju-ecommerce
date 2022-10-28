package cybersoft.javabackend.java18.obajuecommerce.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String reqPath = request.getRequestURI();
        if (reqPath.equals("/api/v1/auth/login")) {
            filterChain.doFilter(request, response);
        } else {
            String token = jwtUtils.getToken(request);
            if(jwtUtils.validateJwt(token)) {
                String username = jwtUtils.getUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()));
            }
            filterChain.doFilter(request, response);
        }
    }
}
