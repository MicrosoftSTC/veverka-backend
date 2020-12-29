package cz.sharee.backend.domain.profile;

import cz.sharee.backend.domain.enumeration.PostType;
import cz.sharee.backend.domain.flow.Channel;
import cz.sharee.backend.domain.flow.PostDuplication;
import cz.sharee.backend.domain.interfaces.DeleteAble;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Post implements DeleteAble {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 40)
    private String header;

    @Column(nullable = false)
    private String content;

    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate published;

    @ManyToOne
    private User user;

    @ManyToOne
    private Channel channel;

    @OneToMany(mappedBy = "commentedPost")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "votedPost")
    private Set<Vote> votes;

    @OneToMany(mappedBy = "originalPost")
    private Set<PostDuplication> duplicationsFromCurrentPost;

    @OneToMany(mappedBy = "duplicatedPost")
    private Set<PostDuplication> duplicatedPosts;

    // DeleteAble interface implementation

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Boolean deleted = false;

    @Override
    public void delete() {
        this.deleted = true;
    }
}