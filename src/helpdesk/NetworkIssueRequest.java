package helpdesk;

public class NetworkIssueRequest extends AbstractSupportRequest {
    private final int affectedUsers;

    public NetworkIssueRequest(int id, String title, int basePoints, int affectedUsers) {
        super(id, title, basePoints);
        if (affectedUsers <= 0) throw new IllegalArgumentException("Affected users must be positive.");
        if ((long) basePoints + affectedUsers > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Network points exceed integer capacity.");
        this.affectedUsers = affectedUsers;
    }

    public int resolveFor(Technician technician) {
        // Linear rule: base points plus one point per affected user.
        return finishResolution(technician, getBasePoints() + affectedUsers);
    }

    public String toString() {
        return super.toString() + " | network issue: " + affectedUsers + " user(s)";
    }
}
