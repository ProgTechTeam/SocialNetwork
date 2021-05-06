package com.github.progtechteam.socialnetwork.data.entity.chat;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.data.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Evgenii Puliaev
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("2")
public class GroupChat extends Chat {

    @Column(length = 32)
    protected String name;

    @ManyToMany(mappedBy = "groupChats")
    private Set<User> participants = new HashSet<>();

    public GroupChat() {
        super();
        chatType = ChatType.GROUP_CHAT;
    }

}
