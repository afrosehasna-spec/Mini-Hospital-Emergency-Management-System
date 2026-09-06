/**
 * Stack (LIFO) that stores completed treatment records.
 * Implemented with a custom singly linked structure (no java.util.Stack).
 */
public class TreatmentStack {

    // Node of the stack
    private static class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
        }
    }

    private Node top;
    private int size;

    /** Push: add a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for " + record.getPatientName() + " (ID: "
                + record.getPatientId() + ") pushed to treatment history.");
    }

    /** Pop: remove and return the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. No records to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** Peek at the most recent record without removing it. */
    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.record;
    }

    /** Display treatment records, most recent first (LIFO order). */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No treatment history available.");
            return;
        }
        System.out.println("--- Treatment History (Most Recent First) ---");
        Node current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
