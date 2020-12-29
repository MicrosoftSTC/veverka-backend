package cz.sharee.backend.dto.flow;

import cz.sharee.backend.domain.enumeration.CommunityTypes;
import cz.sharee.backend.domain.enumeration.Subjects;
import cz.sharee.backend.domain.profile.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CommunityDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isPrivate;
    private CommunityTypes communityType;
    private Subjects subject;
    private LocalDate founded;
    private User founder;

    private Set<CommunityMembershipDTO> memberships;
    private Set<ChannelDTO> channels;
}
