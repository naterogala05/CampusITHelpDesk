package helpdesk;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HelpDesk {
    private final Map<Integer, SupportRequest> requestsById = new LinkedHashMap<>();
    private final Map<Technician, List<SupportRequest>> assignments = new LinkedHashMap<>();

    public void addRequest(SupportRequest request) {
        if (request == null) throw new IllegalArgumentException("Request is required.");
        if (request.getId() <= 0) throw new IllegalArgumentException("ID must be positive.");
        if (requestsById.containsKey(request.getId()))
            throw new IllegalArgumentException("Duplicate request ID: " + request.getId());
        requestsById.put(request.getId(), request);
    }

    public SupportRequest findRequest(int id) { return requestsById.get(id); }

    public void assignRequest(Technician technician, int requestId) {
        requireTechnician(technician);
        SupportRequest request = findRequest(requestId);
        if (request == null) throw new IllegalArgumentException("Unknown request: " + requestId);
        if (request.isResolved()) throw new IllegalStateException("Cannot assign a resolved request.");
        // One owner per request; duplicate and multi-technician assignments are forbidden.
        for (List<SupportRequest> list : assignments.values()) {
            for (SupportRequest assigned : list) {
                if (assigned.getId() == requestId)
                    throw new IllegalStateException("Request " + requestId + " is already assigned.");
            }
        }
        List<SupportRequest> list = assignments.get(technician);
        if (list == null) {
            list = new ArrayList<>();
            assignments.put(technician, list);
        }
        list.add(request);
    }

    public int resolveRequest(Technician technician, int requestId) {
        requireTechnician(technician);
        // Use the stored key as the canonical technician when equal names are supplied.
        for (Map.Entry<Technician, List<SupportRequest>> entry : assignments.entrySet()) {
            if (entry.getKey().equals(technician)) {
                for (SupportRequest request : entry.getValue()) {
                    if (request.getId() == requestId)
                        return request.resolveFor(entry.getKey());
                }
            }
        }
        throw new IllegalArgumentException("Request " + requestId + " is not assigned to this technician.");
    }

    public void printAllRequests() {
        System.out.println("ALL REQUESTS");
        for (SupportRequest request : requestsById.values()) System.out.println(request);
    }

    public void printAssignmentsFor(Technician technician) {
        requireTechnician(technician);
        System.out.println("Assignments for " + technician);
        List<SupportRequest> list = assignments.get(technician);
        if (list == null || list.isEmpty()) System.out.println("  (none)");
        else for (SupportRequest request : list) System.out.println("  " + request);
    }

    private void requireTechnician(Technician technician) {
        if (technician == null) throw new IllegalArgumentException("Technician is required.");
    }
}
