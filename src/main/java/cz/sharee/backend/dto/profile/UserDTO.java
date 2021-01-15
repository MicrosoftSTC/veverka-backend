package cz.sharee.backend.dto.profile;

import cz.sharee.backend.domain.enumeration.Countries;
import cz.sharee.backend.domain.enumeration.EmailStatus;
import cz.sharee.backend.domain.enumeration.Subjects;
import cz.sharee.backend.dto.flow.ChannelDTO;
import cz.sharee.backend.dto.flow.ChannelMembershipDTO;
import cz.sharee.backend.dto.flow.CommunityDTO;
import cz.sharee.backend.dto.flow.CommunityMembershipDTO;
import cz.sharee.backend.dto.tests.TestDTO;
import cz.sharee.backend.dto.validation.UserLoginInfo;
import cz.sharee.backend.dto.validation.UserRegistrationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Nullable
    private Long id;
    @NotNull(message = "username must not be null", groups = {UserRegistrationInfo.class, UserLoginInfo.class})
    private String username;
    @NotNull(message = "password must not be null", groups = {UserRegistrationInfo.class, UserLoginInfo.class})
    @Size(min = 10, message = "password must be at least 10 characters", groups = {UserRegistrationInfo.class, UserLoginInfo.class})
    private String password;
    @NotNull(message = "email must not be null", groups = {UserRegistrationInfo.class})
    @Email(message = "provided string is not a valid email address", groups = {UserRegistrationInfo.class})
    private String email;
    @Nullable
    private EmailStatus emailStatus;
    @NotNull(message = "firstName must not be null", groups = {UserRegistrationInfo.class})
    private String firstName;
    @NotNull(message = "lastName must not be null", groups = {UserRegistrationInfo.class})
    private String lastName;
    @Nullable
    private LocalDate joined;
    @Nullable
    private String bio;
    @Nullable
    private Subjects favouriteSubject;
    @NotNull(message = "country must not be null",groups = {UserRegistrationInfo.class})
    private Countries country;
    @NotNull(message = "city must not be null", groups = {UserRegistrationInfo.class})
    private String city;
    @Nullable
    private Boolean needsReview;
    @Nullable
    private Boolean banned;
    @Nullable
    private Set<CommunityDTO> foundedCommunities;
    @Nullable
    private Set<CommunityMembershipDTO> activeCommunityMemberships;
    @Nullable
    private Set<ChannelDTO> foundedChannels;
    @Nullable
    private Set<ChannelMembershipDTO> channelMemberships;
    @Nullable
    private Set<PostDTO> posts;
    @Nullable
    private Set<VoteDTO> votes;
    @Nullable
    private Set<CommentDTO> comments;
    @Nullable
    private Set<StarDTO> stars;
    @Nullable
    private Set<SocialSiteMembershipDTO> socialSiteMemberships;
    @Nullable
    private Set<FollowDTO> followedBy;
    @Nullable
    private Set<ActivityDTO> activities;
    @Nullable
    private Set<TestDTO> createdTests;
}
