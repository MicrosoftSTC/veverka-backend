package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarDTO {
    private Long id;
    private UserDTO starPublisher;
    private CommentDTO comment;
}
