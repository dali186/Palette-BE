package fc.server.palette.member.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class AuthoritiesProvider {
    private static final GrantedAuthority AUTHORITY = new SimpleGrantedAuthority("ROLE_MEMBER");

    public static Collection<GrantedAuthority> getAuthorityCollection() {
        return List.of(AUTHORITY);
    }
}
