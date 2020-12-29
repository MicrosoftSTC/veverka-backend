package cz.sharee.backend.domain.flow;

import cz.sharee.backend.domain.enumeration.CommunityMembershipStatus;
import cz.sharee.backend.domain.enumeration.Roles;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class CommunityMembership {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate since;

    // field for checking a date of last role change
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate lastModified;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Enumerated(EnumType.STRING)
    private CommunityMembershipStatus status;

    @ManyToOne
    private User member;

    @ManyToOne
    private Community community;
}
