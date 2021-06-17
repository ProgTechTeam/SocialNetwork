package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.data.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Evgenii Puliaev
 */
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c.chatType from Chat c where c.id = :chatId")
    Optional<ChatType> getChatTypeById(@Param("chatId") Integer chatId);

}
