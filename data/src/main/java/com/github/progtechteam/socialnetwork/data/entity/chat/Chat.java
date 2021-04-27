package com.github.progtechteam.socialnetwork.data.entity.chat;

import com.github.progtechteam.socialnetwork.commons.ChatType;
import com.github.progtechteam.socialnetwork.data.entity.Message;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Data
@Entity
@Table(name = "chats", schema = "entities")
@Inheritance
@DiscriminatorColumn(name = "chat_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", updatable = false)
    protected Integer id;

    @Column(name = "chat_type", nullable = false, insertable = false, updatable = false)
    protected ChatType chatType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "chat")
    protected List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        message.setChat(this);
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
        message.setChat(null);
    }
}
