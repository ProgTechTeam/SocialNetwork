package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evgenii Puliaev
 */
public interface MessageRepository extends JpaRepository<Message, Integer>  {
}
