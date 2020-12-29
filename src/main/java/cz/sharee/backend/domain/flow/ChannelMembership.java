package cz.sharee.backend.domain.flow;

import cz.sharee.backend.domain.enumeration.Roles;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class ChannelMembership {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate since;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToOne
    private User member;

    @ManyToOne
    private Channel channel;
}
