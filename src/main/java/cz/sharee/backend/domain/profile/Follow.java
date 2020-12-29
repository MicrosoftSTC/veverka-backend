package cz.sharee.backend.domain.profile;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class Follow {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followed;

    @CreationTimestamp
    private LocalDate since;
}
