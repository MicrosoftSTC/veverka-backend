package cz.sharee.backend.domain.chat;

import cz.sharee.backend.domain.enumeration.ChatRoomStatus;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDate createdAt;

    @Column(nullable = false)
    private ChatRoomStatus chatRoomStatus = ChatRoomStatus.REQUESTED;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User acceptor;

    @OneToMany(mappedBy = "chatRoom")
    private List<Message> messages;
}
