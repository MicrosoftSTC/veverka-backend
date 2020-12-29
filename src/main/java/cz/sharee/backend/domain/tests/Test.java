package cz.sharee.backend.domain.tests;

import cz.sharee.backend.domain.TestStatus;
import cz.sharee.backend.domain.enumeration.Subjects;
import cz.sharee.backend.domain.enumeration.TestType;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Badge badge;

    @OneToMany
    private Set<Question> questions;

    @ManyToOne
    private User creator;

    @OneToMany(mappedBy = "reportedTest")
    private Set<TestReport> testReports;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Subjects subject;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TestType type;

    // community test has to be approved in admin console
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TestStatus testStatus = TestStatus.WAITING_FOR_APPROVAL;
}
