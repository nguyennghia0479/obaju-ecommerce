package cybersoft.javabackend.java18.obajuecommerce.security.jwt;

import cybersoft.javabackend.java18.obajuecommerce.user.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {
    private final String secretKey = """
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            CybersoftEducation
            """;
    private final String PREFIX = "Bearer";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String generateJwt(User user) {
        Date currentDate = new Date();
        List<String> userGroups = new ArrayList<>();
        user.getUserGroups().forEach(ug -> userGroups.add(ug.getName()));
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("scope", userGroups)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + 8640000))
                .signWith(
                        SECRET_KEY,
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateJwt(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch(UnsupportedJwtException e1) {
            log.error("Jwt is not supported: " + e1);
        } catch (MalformedJwtException e2) {
            log.error("Invalid token: " + e2);
        } catch(SignatureException e3) {
            log.error("Invalid signature: " + e3);
        } catch (ExpiredJwtException e4) {
            log.error("Jwt is expired: " + e4);
        } catch (IllegalArgumentException e5) {
            log.error("JWT Claims is empty: " + e5);
        }
        return false;
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if(jwt == null)
            return null;
        return jwt.substring(PREFIX.length(), jwt.length());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
