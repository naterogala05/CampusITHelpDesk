package helpdesk;

import java.util.List;

public final class HelpDeskUtil {
    private HelpDeskUtil() { }

    public static <T> void printAll(List<T> items) {
        if (items == null) throw new IllegalArgumentException("List is required.");
        for (T item : items) {
            if (item == null) throw new IllegalArgumentException("Items must not be null.");
            System.out.println(item.toString());
        }
    }

    public static int sumPoints(List<? extends Technician> technicians) {
        if (technicians == null) throw new IllegalArgumentException("List is required.");
        long total = 0;
        for (Technician technician : technicians) {
            if (technician == null) throw new IllegalArgumentException("Technician is required.");
            total += technician.getServicePoints();
            if (total > Integer.MAX_VALUE)
                throw new IllegalArgumentException("Total points exceed integer capacity.");
        }
        return (int) total;
    }
}
