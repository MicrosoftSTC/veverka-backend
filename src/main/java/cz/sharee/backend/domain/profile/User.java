package cz.sharee.backend.domain.profile;

import cz.sharee.backend.domain.chat.ChatRoom;
import cz.sharee.backend.domain.chat.Message;
import cz.sharee.backend.domain.enumeration.*;
import cz.sharee.backend.domain.flow.Channel;
import cz.sharee.backend.domain.flow.ChannelMembership;
import cz.sharee.backend.domain.flow.Community;
import cz.sharee.backend.domain.flow.CommunityMembership;
import cz.sharee.backend.domain.interfaces.DeleteAble;
import cz.sharee.backend.domain.security.Authority;
import cz.sharee.backend.domain.security.Role;
import cz.sharee.backend.domain.tests.BadgeClaim;
import cz.sharee.backend.domain.tests.Test;
import cz.sharee.backend.domain.tests.TestReport;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, DeleteAble {
    @Id
    @GeneratedValue
    private Long id;

    // names
    @NotNull
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailStatus emailStatus = EmailStatus.NOT_VERIFIED;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    // dates
    @NotNull
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate joined;

    @NotNull
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate lastModified;

    // user details
    @Column(length = 100)
    private String bio;

    @Enumerated(EnumType.STRING)
    private Subjects favouriteSubject;

//    @Column(length = 100, nullable = true)
//    private String activities;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Countries country;

    @NotNull
    @Column(nullable = false)
    private String city;

    private String street;

    private Integer doorNumber;

    private String schoolName;

    // relationships

    // founded communities
    @OneToMany(mappedBy = "founder")
    @OrderColumn(name = "FOUNDED")
    @Singular
    private Set<Community> foundedCommunities;

    @OneToMany(mappedBy = "member")
    @Singular
    private Set<CommunityMembership> allCommunityMemberships;

    @Transient
    private Set<CommunityMembership> activeCommunityMemberships;

    // founded channels
    @OneToMany(mappedBy = "founder")
    @Singular
    private Set<Channel> foundedChannels;

    @OneToMany(mappedBy = "member")
    @Singular
    private Set<ChannelMembership> channelMemberships;

    @OneToMany(mappedBy = "user")
    @Singular
    private Set<Post> posts;

    @OneToMany(mappedBy = "user")
    @Singular
    private Set<Vote> votes;

    @OneToMany(mappedBy = "publisher")
    @Singular
    private Set<Comment> comments;

    @OneToMany(mappedBy = "starPublisher")
    @Singular
    private Set<Star> stars;

    @OneToMany(mappedBy = "socialSiteMember")
    @Singular
    private Set<SocialSiteMembership> socialSiteMemberships;

    // implementation of follows
    @OneToMany(mappedBy = "follower")
    @Singular
    private Set<Follow> followings;

    @OneToMany(mappedBy = "followed")
    @Singular
    private Set<Follow> followedBys;

    // activities that are searchable by badging them
    @OneToMany(mappedBy = "user")
    @Singular
    private Set<Activity> activities;

    @OneToMany(mappedBy = "creator")
    @Singular
    private Set<Test> createdTests;

    @OneToMany(mappedBy = "claimer")
    @Singular
    private Set<BadgeClaim> badgeClaims;

    @OneToMany(mappedBy = "reporter")
    @Singular
    private Set<TestReport> currentUserTestReports;

    @OneToMany(mappedBy = "requester")
    @Singular
    private Set<ChatRoom> requestedChatRooms;

    @OneToMany(mappedBy = "acceptor")
    @Singular
    private Set<ChatRoom> acceptedChatRooms;

    @Transient
    private Set<ChatRoom> activeChatRooms;

    @OneToMany(mappedBy = "sender")
    @Singular
    private List<Message> messages;

    @PostLoad
    public void transientLoader(){
        // activeCommunityMemberships loader
        this.activeCommunityMemberships = new HashSet<>(allCommunityMemberships);
        this.activeCommunityMemberships = this.activeCommunityMemberships.stream().filter(communityMembership -> communityMembership.getStatus() != CommunityMembershipStatus.BANNED).collect(Collectors.toSet());

        // activeChatRooms loader
        this.activeChatRooms = new HashSet<>(acceptedChatRooms);
        activeChatRooms.addAll(requestedChatRooms);
        this.activeChatRooms = this.acceptedChatRooms.stream().filter(chatRoom -> chatRoom.getChatRoomStatus() == ChatRoomStatus.ACCEPTED).collect(Collectors.toSet());

        // set authorities
        this.authorities = this.roles.stream().map(role -> role.getAuthorities()).flatMap(Set::stream).collect(Collectors.toSet());
    }

    // UserDetails implementation
    @Column(nullable = false)
    @Builder.Default
    private Boolean isAccountNonExpired = true;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isAccountNonLocked = true;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isCredentialsNonExpired = true;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isEnabled = true;

    @ManyToMany
    private Set<Role> roles;

    @Transient
    private Set<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    // DeleteAble interface implementation

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private Boolean deleted = false;

    @Override
    public void delete() {
        this.deleted = true;
    }
}
