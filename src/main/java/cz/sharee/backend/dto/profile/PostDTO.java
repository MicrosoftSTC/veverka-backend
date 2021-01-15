package cz.sharee.backend.dto.profile;

import cz.sharee.backend.domain.enumeration.PostType;
import cz.sharee.backend.domain.flow.PostDuplication;
import cz.sharee.backend.dto.flow.ChannelDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PostDTO {
    private Long id;
    @Nullable
    private String header;
    private String content;
    private PostType postType;
    private LocalDate published;
    private UserDTO user;
    private ChannelDTO channel;
    private Set<VoteDTO> votes;
    private Set<PostDuplication> duplicationsFromCurrentPost;
    private Set<PostDuplication> duplicatedPosts;
    private Boolean deleted = false;
}
