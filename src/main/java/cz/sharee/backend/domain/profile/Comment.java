package cz.sharee.backend.domain.profile;

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
public class Comment implements DeleteAble {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private String content;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate created;

    @ManyToOne
    private User publisher;

    @ManyToOne
    private Post commentedPost;

    @OneToMany(mappedBy = "comment")
    private Set<Star> stars;

    @OneToMany(mappedBy = "commentParent") // property in other Comment
    private Set<Comment> commentsChildren;

    @ManyToOne
    private Comment commentParent;

    // DeleteAble interface implementation

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Boolean deleted = false;

    @Override
    public void delete() {
        this.deleted = true;
    }
}
