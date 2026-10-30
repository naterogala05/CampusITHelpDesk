package helpdesk;

public interface SupportRequest {
    int getId();
    String getTitle();
    int getBasePoints();
    boolean isResolved();
    int resolveFor(Technician technician);
}
