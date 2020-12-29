package cz.sharee.backend.dto.profile;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String content;
    private LocalDate created;
    private UserDTO publisher;
    private PostDTO commentedPost;
    private Set<StarDTO> stars;
    private Set<CommentDTO> commentsChildren;
    private CommentDTO commentParent;

}
