package cz.sharee.backend.domain.flow;

import cz.sharee.backend.domain.enumeration.CommunityTypes;
import cz.sharee.backend.domain.enumeration.Subjects;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Community {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isPrivate;

    @Column(nullable = false)
    private CommunityTypes communityType;

    @Enumerated(EnumType.STRING)
    @Nullable
    private Subjects subject;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate founded;

    @ManyToOne
    private User founder;

    @OneToMany(mappedBy = "community")
    private Set<CommunityMembership> memberships;

    @OneToMany(mappedBy = "community")
    private Set<Channel> channels;
}
