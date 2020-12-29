package cz.sharee.backend.domain.chat;

import cz.sharee.backend.domain.interfaces.DeleteAble;
import cz.sharee.backend.domain.profile.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Message implements DeleteAble {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime created;

    // if message is deleted, it won't be deleted from db, but the text will be set to something else
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Boolean deleted = false;

    @ManyToOne
    private ChatRoom chatRoom;

    @ManyToOne
    private User sender;

    @Override
    public void delete() {
        this.deleted = true;
    }
}
