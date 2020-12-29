package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDTO {
    private Long id;
    private Boolean value;
    private UserDTO user;
    private PostDTO votedPost;
}
