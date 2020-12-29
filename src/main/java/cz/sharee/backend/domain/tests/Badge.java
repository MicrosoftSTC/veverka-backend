package cz.sharee.backend.domain.tests;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Data
public class Badge {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Test test;

    @OneToMany(mappedBy = "claimedBadge")
    private Set<BadgeClaim> badgeClaims;

    @Column(nullable = false)
    @Positive
    private Byte scorePoints;

    @Column(nullable = false)
    private String description;
}
