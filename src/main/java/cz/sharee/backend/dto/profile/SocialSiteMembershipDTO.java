package cz.sharee.backend.dto.profile;

import cz.sharee.backend.domain.profile.SocialSite;
import cz.sharee.backend.domain.profile.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialSiteMembershipDTO {
    private Long id;
    private User socialSiteMember;
    private SocialSite socialSite;
    private String username;
}
