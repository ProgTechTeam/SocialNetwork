package com.github.progtechteam.socialnetwork.data.entity.chat;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.data.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("1")
public class PrivateChat extends Chat {

    @ManyToOne
    @JoinColumn(name = "first_participant_user_id", updatable = false)
    private User firstParticipant;

    @ManyToOne
    @JoinColumn(name = "second_participant_user_id", updatable = false)
    private User secondParticipant;

    public PrivateChat() {
        super();
        chatType = ChatType.PRIVATE_CHAT;
    }

}
