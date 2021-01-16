package cz.sharee.backend.domain.profile;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class SocialSite {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 40)
    private String url;

    @OneToMany(mappedBy = "socialSite")
    private Set<SocialSiteMembership> socialSiteMembership;
}