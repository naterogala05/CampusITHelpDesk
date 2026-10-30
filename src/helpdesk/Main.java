package helpdesk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("CAMPUS IT HELP DESK DEMO");
        Technician alex = new Technician("Alex");
        Technician jordan = new Technician("Jordan");
        Technician duplicateAlex = new Technician("Alex");
        Technician sam = new Technician("Sam");
        Set<Technician> unique = new HashSet<>();
        unique.add(alex);
        unique.add(jordan);
        System.out.println("Adding another Alex: " + unique.add(duplicateAlex));
        unique.add(sam);
        System.out.println("Four objects, unique technician names: " + unique.size());
        // Fixed report order; HashSet itself does not guarantee iteration order.
        List<Technician> technicians = new ArrayList<>();
        technicians.add(alex);
        technicians.add(jordan);
        technicians.add(sam);

        HelpDesk desk = new HelpDesk();
        desk.addRequest(new PasswordResetRequest(1, "Student account reset", 5, "narcqp"));
        desk.addRequest(new PasswordResetRequest(2, "Faculty account reset", 7, "faculty01"));
        desk.addRequest(new DeviceSetupRequest(3, "Library laptop setup", 10, 3));
        desk.addRequest(new DeviceSetupRequest(4, "Office workstation setup", 8, 2));
        desk.addRequest(new NetworkIssueRequest(5, "Residence hall Wi-Fi", 12, 25));
        desk.addRequest(new NetworkIssueRequest(6, "Lab connection issue", 6, 4));
        desk.assignRequest(alex, 1);
        desk.assignRequest(alex, 3);
        desk.assignRequest(jordan, 2);
        desk.assignRequest(jordan, 5);
        desk.assignRequest(jordan, 4);
        System.out.println("Resolved #1: +" + desk.resolveRequest(alex, 1));
        System.out.println("Resolved #3: +" + desk.resolveRequest(alex, 3));
        System.out.println("Resolved #5: +" + desk.resolveRequest(jordan, 5));
        System.out.println("Missing request #999: " + desk.findRequest(999));

        System.out.println("\nEXPECTED FAIL-FAST EXCEPTIONS");
        try {
            desk.addRequest(new PasswordResetRequest(1, "Duplicate", 5, "other"));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
        try {
            desk.resolveRequest(alex, 1);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
        try {
            desk.assignRequest(alex, 4);
        } catch (IllegalStateException exception) {
            System.out.println(exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
        try {
            new DeviceSetupRequest(7, "Invalid device count", 10, 0);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
        
        // Additional validation tests
        System.out.println("\nADDITIONAL VALIDATION TESTS");

        // Test that an invalid technician name is rejected
        try {
            new Technician("");
        } catch (IllegalArgumentException exception) {
            System.out.println("Blank technician name rejected: "
                    + exception.getMessage());
        }

        // Test that a request with invalid base points is rejected
        try {
            new PasswordResetRequest(8, "Invalid points", 0, "student01");
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid base points rejected: "
                    + exception.getMessage());
        }

        System.out.println();
        desk.printAllRequests();
        System.out.println();
        for (Technician technician : technicians) desk.printAssignmentsFor(technician);
        System.out.println("\nFINAL TECHNICIAN TOTALS");
        HelpDeskUtil.printAll(technicians);
        System.out.println("Total service points: " + HelpDeskUtil.sumPoints(technicians));
    }
}
