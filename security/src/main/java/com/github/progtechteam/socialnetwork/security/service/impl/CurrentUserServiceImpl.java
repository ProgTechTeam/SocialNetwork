package com.github.progtechteam.socialnetwork.security.service.impl;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.data.repository.AccountRepository;
import com.github.progtechteam.socialnetwork.security.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private final AccountRepository accountRepository;

    @Override
    public CurrentUser loadByUsername(String username) {
        return accountRepository
                .findByEmail(username)
                .map(CurrentUser::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with email [%s] not found", username))
                );
    }
}
