/**
 * Singly Linked List that stores a patient's previous hospital visits.
 * Supports adding, removing, searching, and displaying visits.
 */
public class VisitLinkedList {

    // Node of the singly linked list
    private static class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
        }
    }

    private Node head;
    private int size;

    /** Add a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Remove a visit by its Visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Search for a visit by Visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /** Display all visits in this patient's history, in order added. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("  No visit history available for this patient.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.visit);
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
