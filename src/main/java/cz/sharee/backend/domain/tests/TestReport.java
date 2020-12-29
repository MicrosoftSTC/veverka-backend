package cz.sharee.backend.domain.tests;

import cz.sharee.backend.domain.enumeration.TestReportCause;
import cz.sharee.backend.domain.profile.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class TestReport {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDate issuedAt;

    @Column(nullable = false)
    private Boolean value;

    // comment why the test report is negative
    private String comment;

    // causes why the test report is negative
    // same logic as in counter strike, multiple choices
    @ElementCollection(targetClass= TestReportCause.class)
    @Enumerated(EnumType.STRING)
    private Set<TestReportCause> testReportCauses;

    @ManyToOne
    private User reporter;

    @ManyToOne
    private Test reportedTest;
}
