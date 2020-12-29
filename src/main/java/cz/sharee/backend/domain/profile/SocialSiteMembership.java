package cz.sharee.backend.domain.profile;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SocialSiteMembership {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User socialSiteMember;

    @ManyToOne
    private SocialSite socialSite;

    @Column(nullable = false)
    private String username;
}
