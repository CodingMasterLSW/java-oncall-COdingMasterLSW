package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentResults {

    private List<AssignmentResult> assignmentResults;

    private AssignmentResults() {
        this.assignmentResults = new ArrayList<>();
    }

    public static AssignmentResults create() {
        return new AssignmentResults();
    }

    public List<AssignmentResult> getAssignmentResults() {
        return Collections.unmodifiableList(assignmentResults);
    }

    public void addResult(AssignmentResult assignmentResult) {
        assignmentResults.add(assignmentResult);
    }
}
