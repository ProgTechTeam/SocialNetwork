package com.github.progtechteam.socialnetwork.security.model;

import com.github.progtechteam.socialnetwork.commons.Role;
import com.github.progtechteam.socialnetwork.data.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Implements {@link UserDetails}.
 * Contains user's data: username(email), password and authorities.
 *
 * @author Evgenii Puliaev
 */
@Getter
public class AuthenticatedUserDetails implements UserDetails {

    private final Integer id;
    private final String email;
    private final String password;
    private final Role role;
    private final Collection<SimpleGrantedAuthority> authorities;

    public AuthenticatedUserDetails(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.role = account.getRole();
        this.authorities = Set.of(new SimpleGrantedAuthority(account.getRole().getSystemName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
