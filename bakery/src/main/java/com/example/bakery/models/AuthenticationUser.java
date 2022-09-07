package com.example.bakery.models;

import com.example.bakery.models.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationUser implements UserDetails {

    private String token;
    private String username;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    /**
     * Create AuthUser with password property set.
     *
     * @param user the user entity.
     */
    public static AuthenticationUser forInternal(User user) {
        AuthenticationUser authUser = new AuthenticationUser(user);
        authUser.password = user.getPassword();
        return authUser;
    }

    /**
     * Create AuthUser without password property set.
     *
     * @param user the user entity.
     */
    public static AuthenticationUser forExternal(User user) {
        return new AuthenticationUser(user);
    }

    /**
     * Create AuthUser without password property set.
     *
     * @param token the random generated token for the user.
     */
    public static AuthenticationUser createAnonymous(String token) {
        AuthenticationUser authUser = new AuthenticationUser();
        authUser.token = token;
        authUser.username = "GUEST";
        authUser.authorities = Collections.singletonList(() -> "GUEST");
        return authUser;
    }

    private AuthenticationUser(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.token = user.getToken();
        this.authorities = Collections.singletonList(user::getRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRole() {
        return this.authorities.get(0).getAuthority();
    }
}
