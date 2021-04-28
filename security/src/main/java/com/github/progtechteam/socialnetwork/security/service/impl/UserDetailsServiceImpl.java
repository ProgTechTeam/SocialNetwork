package com.github.progtechteam.socialnetwork.security.service.impl;

import com.github.progtechteam.socialnetwork.data.repository.AccountRepository;
import com.github.progtechteam.socialnetwork.security.model.SnUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements {@link UserDetailsService}.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    /**
     * Used to check if user with given username exists in database.
     *
     * @param username user's email
     * @return {@link UserDetails} object with user's information and authorities
     * @throws UsernameNotFoundException if user has not been found in database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository
                .findByEmail(username)
                .map(SnUserDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with email [%s] not found", username))
                );
    }
}
