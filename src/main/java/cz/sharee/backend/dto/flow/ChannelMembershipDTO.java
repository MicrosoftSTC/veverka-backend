package cz.sharee.backend.dto.flow;

import cz.sharee.backend.domain.enumeration.Roles;
import cz.sharee.backend.dto.profile.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ChannelMembershipDTO {
    private Long id;
    private LocalDate since;
    private Roles role;
    private UserDTO member;
    private ChannelDTO channel;
}
