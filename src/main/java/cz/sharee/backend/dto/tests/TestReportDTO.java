package cz.sharee.backend.dto.tests;

import cz.sharee.backend.domain.enumeration.TestReportCause;
import cz.sharee.backend.dto.profile.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TestReportDTO {
    private Long id;
    private LocalDate issuedAt;
    private String comment;
    private Set<TestReportCause> testReportCauses;
    private UserDTO reporter;
    private TestDTO reportedTest;
}
