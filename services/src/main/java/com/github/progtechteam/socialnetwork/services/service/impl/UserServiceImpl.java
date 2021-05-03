package com.github.progtechteam.socialnetwork.services.service.impl;

import com.github.progtechteam.socialnetwork.commons.Role;
import com.github.progtechteam.socialnetwork.data.entity.Account;
import com.github.progtechteam.socialnetwork.data.entity.User;
import com.github.progtechteam.socialnetwork.data.repository.AccountRepository;
import com.github.progtechteam.socialnetwork.data.repository.UserRepository;
import com.github.progtechteam.socialnetwork.services.exception.EntityNotFoundException;
import com.github.progtechteam.socialnetwork.services.exception.UserAlreadyExistsException;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;
import com.github.progtechteam.socialnetwork.services.service.AuthenticationService;
import com.github.progtechteam.socialnetwork.services.service.PasswordEncoderService;
import com.github.progtechteam.socialnetwork.services.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Evgenii Puliaev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final AuthenticationService authenticationService;

    @Override
    public CurrentUser create(UserCreateDto dto) {
        final var alreadyExists = accountRepository.findByEmail(dto.getEmail()).isPresent();
        if (alreadyExists) {
            throw new UserAlreadyExistsException(
                    String.format("Email [{%s}] already registered in system", dto.getEmail())
            );
        }

        final var newUser = new User();
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        final var savedUser = userRepository.saveAndFlush(newUser);

        final var newAccount = new Account();
        newAccount.setEmail(dto.getEmail());
        newAccount.setRole(Role.USER);
        newAccount.setPassword(passwordEncoderService.encode(dto.getPassword()));
        newAccount.setAccountOwner(savedUser);
        accountRepository.saveAndFlush(newAccount);

        final var account = accountRepository
                .findById(savedUser.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "User with [ID={%d}] not found",
                        savedUser.getId()
                )));
        log.info("User with [email={}] was successfully created", account.getEmail());
        return authenticationService.authenticate(dto.getEmail(), dto.getPassword());
    }
}
