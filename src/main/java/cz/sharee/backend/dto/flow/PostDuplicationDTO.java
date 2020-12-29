package cz.sharee.backend.dto.flow;

import cz.sharee.backend.domain.enumeration.PostDuplicationStatus;
import cz.sharee.backend.dto.profile.PostDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostDuplicationDTO {
    private Long id;
    private LocalDate issuedAt;
    private PostDuplicationStatus postDuplicationStatus;
    private PostDTO originalPost;
    private PostDTO duplicatedPostDTO;
}
