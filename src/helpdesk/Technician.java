package helpdesk;

public class Technician {
    private final String name;
    private int servicePoints;

    public Technician(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Technician name must not be blank.");
        this.name = name;
    }

    public final void addPoints(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Points must be positive.");
        if ((long) servicePoints + amount > Integer.MAX_VALUE)
            throw new IllegalArgumentException("Service points exceed integer capacity.");
        servicePoints += amount;
    }

    public final int getServicePoints() { return servicePoints; }

    public final boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Technician)) return false;
        Technician technician = (Technician) other;
        return name.equals(technician.name);
    }

    public final int hashCode() { return name.hashCode(); }

    public String toString() { return name + " | service points=" + servicePoints; }
}
