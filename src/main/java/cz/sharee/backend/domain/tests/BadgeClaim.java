package cz.sharee.backend.domain.tests;

import cz.sharee.backend.domain.profile.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class BadgeClaim {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User claimer;

    @ManyToOne
    private Badge claimedBadge;
}
