package cz.sharee.backend.domain.flow;

import cz.sharee.backend.domain.enumeration.PostDuplicationStatus;
import cz.sharee.backend.domain.profile.Post;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class PostDuplication {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDate issuedAt;

    @Enumerated(EnumType.STRING)
    private PostDuplicationStatus postDuplicationStatus;

    @ManyToOne
    private Post originalPost;

    @ManyToOne
    private Post duplicatedPost;
}
