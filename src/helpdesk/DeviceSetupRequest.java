package helpdesk;

public class DeviceSetupRequest extends AbstractSupportRequest {
    private final int deviceCount;

    public DeviceSetupRequest(int id, String title, int basePoints, int deviceCount) {
        super(id, title, basePoints);
        if (deviceCount <= 0) throw new IllegalArgumentException("Device count must be positive.");
        if ((long) basePoints * deviceCount > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Device points exceed integer capacity.");
        this.deviceCount = deviceCount;
    }

    public int resolveFor(Technician technician) {
        return finishResolution(technician, getBasePoints() * deviceCount);
    }

    public String toString() {
        return super.toString() + " | device setup: " + deviceCount + " device(s)";
    }
}
