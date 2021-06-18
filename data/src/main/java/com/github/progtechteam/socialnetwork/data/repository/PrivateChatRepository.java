package com.github.progtechteam.socialnetwork.data.repository;

import com.github.progtechteam.socialnetwork.data.entity.chat.PrivateChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
public interface PrivateChatRepository extends JpaRepository<PrivateChat, Integer> {

    @Query("from PrivateChat pc where pc.firstParticipant.id = :userId or pc.secondParticipant.id = :userId")
    List<PrivateChat> findChatsByParticipantId(@Param("userId") Integer participantId);

    @Query("select pc.id " +
            "from PrivateChat pc " +
            "where (pc.firstParticipant.id = :firstUserId or pc.secondParticipant.id = :firstUserId) " +
            "and (pc.firstParticipant.id = :secondUserId or pc.secondParticipant.id = :secondUserId)")
    Integer findChatIdByParticipantsId(@Param("firstUserId") Integer firstUserId,
                                       @Param("secondUserId") Integer secondUserId);

}
