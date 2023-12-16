package br.com.api.core.security;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

import static java.lang.Boolean.TRUE;

public class ApiAuthentication extends AbstractAuthenticationToken {

    private final UserDetails userDetails;
    private final JWTClaimsSet jwtClaimsSet;
    private final String token;

    public ApiAuthentication(Collection<? extends GrantedAuthority> authorities, UserDetails userDetails, JWTClaimsSet jwtClaimsSet, String token) {
        super(authorities);
        super.setAuthenticated(TRUE);
        super.setDetails(userDetails);
        this.userDetails = userDetails;
        this.jwtClaimsSet = jwtClaimsSet;
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ApiAuthentication that = (ApiAuthentication) o;
        return Objects.equals(userDetails, that.userDetails) && Objects.equals(jwtClaimsSet, that.jwtClaimsSet) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userDetails, jwtClaimsSet, token);
    }
}
