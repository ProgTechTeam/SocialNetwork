package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evgenii Puliaev
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
