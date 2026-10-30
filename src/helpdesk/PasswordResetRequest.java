package helpdesk;

public class PasswordResetRequest extends AbstractSupportRequest {
    private final String accountName;

    public PasswordResetRequest(int id, String title, int basePoints, String accountName) {
        super(id, title, basePoints);
        if (accountName == null || accountName.isBlank())
            throw new IllegalArgumentException("Account name must not be blank.");
        this.accountName = accountName;
    }

    public int resolveFor(Technician technician) {
        return finishResolution(technician, getBasePoints());
    }

    public String toString() {
        return super.toString() + " | password reset: " + accountName;
    }
}
