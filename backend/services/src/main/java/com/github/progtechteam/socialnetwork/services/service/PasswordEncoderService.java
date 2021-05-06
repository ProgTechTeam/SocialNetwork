package com.github.progtechteam.socialnetwork.services.service;

/**
 * Service to interact with PasswordEncoder.
 * Encodes given raw password with system password encoder.
 *
 * @author Evgenii Puliaev
 */
public interface PasswordEncoderService {

    String encode(String password);

}
