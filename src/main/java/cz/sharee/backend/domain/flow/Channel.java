package cz.sharee.backend.domain.flow;

import cz.sharee.backend.domain.profile.Post;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Channel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    private Community community;

    @ManyToOne
    private User founder;

    @OneToMany(mappedBy = "channel")
    private Set<Post> posts;

    @OneToMany(mappedBy = "channel")
    private Set<ChannelMembership> channelMemberships;
}
