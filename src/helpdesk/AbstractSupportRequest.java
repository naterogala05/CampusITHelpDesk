package helpdesk;

public abstract class AbstractSupportRequest implements SupportRequest {
    private final int id;
    private final String title;
    private final int basePoints;
    private boolean resolved;

    protected AbstractSupportRequest(int id, String title, int basePoints) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title must not be blank.");
        if (basePoints <= 0) throw new IllegalArgumentException("Base points must be positive.");
        this.id = id;
        this.title = title;
        this.basePoints = basePoints;
    }

    public final int getId() { return id; }
    public final String getTitle() { return title; }
    public final int getBasePoints() { return basePoints; }
    public final boolean isResolved() { return resolved; }

    protected final int finishResolution(Technician technician, int points) {
        // A request may be resolved exactly once, regardless of technician.
        if (resolved) throw new IllegalStateException("Request " + id + " is already resolved.");
        if (technician == null) throw new IllegalArgumentException("Technician is required.");
        technician.addPoints(points); // Validate/award before changing request state.
        resolved = true;
        return points;
    }

    public String toString() {
        return "#" + id + " " + title + " | base=" + basePoints
                + " | " + (resolved ? "RESOLVED" : "OPEN");
    }
}
