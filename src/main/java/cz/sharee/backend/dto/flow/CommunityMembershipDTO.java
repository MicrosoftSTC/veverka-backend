package cz.sharee.backend.dto.flow;

import cz.sharee.backend.domain.enumeration.CommunityMembershipStatus;
import cz.sharee.backend.domain.enumeration.Roles;
import cz.sharee.backend.dto.profile.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommunityMembershipDTO {
    private Long id;
    private LocalDate since;
    private LocalDate lastModified;
    private Roles role;
    private CommunityMembershipStatus status;
    private UserDTO member;
    private CommunityDTO community;
}
