package cz.sharee.backend.domain.enumeration;

public enum PostDuplicationStatus {
    // user with some score can report the duplication
    // user with a lot of score has to confirm the duplication
    REQUESTED_DUPLICATION, APPROVED_DUPLICATION
}
