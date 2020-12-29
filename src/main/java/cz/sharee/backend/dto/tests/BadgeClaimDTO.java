package cz.sharee.backend.dto.tests;

import cz.sharee.backend.domain.profile.User;
import cz.sharee.backend.domain.tests.Badge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadgeClaimDTO {
    private Long id;
    private User claimer;
    private Badge claimedBadge;
}
