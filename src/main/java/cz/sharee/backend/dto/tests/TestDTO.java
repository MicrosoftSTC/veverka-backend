package cz.sharee.backend.dto.tests;

import cz.sharee.backend.domain.TestStatus;
import cz.sharee.backend.domain.enumeration.Subjects;
import cz.sharee.backend.domain.enumeration.TestType;
import cz.sharee.backend.dto.profile.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TestDTO {
    private Long id;
    private BadgeDTO badge;
    private Set<QuestionDTO> questions;
    private UserDTO creator;
    private Set<TestReportDTO> testReports;
    private Subjects subject;
    private TestType type;
    private TestStatus testStatus = TestStatus.WAITING_FOR_APPROVAL;
}
