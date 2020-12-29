package cz.sharee.backend.domain.profile;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Vote {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Boolean value;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post votedPost;
}
