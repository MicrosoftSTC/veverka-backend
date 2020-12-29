package cz.sharee.backend.dto.flow;

import cz.sharee.backend.dto.profile.PostDTO;
import cz.sharee.backend.dto.profile.UserDTO;

import java.util.Set;

public class ChannelDTO {
    private Long id;
    private String name;
    private CommunityDTO community;
    private UserDTO founder;
    private Set<PostDTO> posts;
}
