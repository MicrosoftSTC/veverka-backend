package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SocialSiteDTO {
    private Long id;
    private String name;
    private String uri;
    private Set<SocialSiteMembershipDTO> socialSiteMembership;
}