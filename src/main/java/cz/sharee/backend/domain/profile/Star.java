package cz.sharee.backend.domain.profile;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Star {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @CreationTimestamp
    private LocalDate published;

    @ManyToOne
    private User starPublisher;

    @ManyToOne
    private Comment comment;
}
