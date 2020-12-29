package cz.sharee.backend.domain.profile;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Star {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User starPublisher;

    @ManyToOne
    private Comment comment;
}
