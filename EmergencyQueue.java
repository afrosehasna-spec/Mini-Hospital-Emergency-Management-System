/**
 * Queue (FIFO) used to manage patients waiting in the emergency unit.
 * Implemented with a custom singly linked structure (no java.util.Queue).
 */
public class EmergencyQueue {

    // Node of the queue
    private static class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node front; // next patient to be treated
    private Node rear;  // most recently added patient
    private int size;

    /** Enqueue: add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " (ID: " + patient.getPatientId()
                + ") added to the emergency queue.");
    }

    /** Dequeue: remove and return the next patient for treatment. */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty. No patients waiting.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        return patient;
    }

    /** Peek at the next patient without removing them. */
    public Patient peekNext() {
        if (isEmpty()) {
            return null;
        }
        return front.patient;
    }

    /** Display all patients currently waiting, in FIFO order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is currently empty.");
            return;
        }
        System.out.println("--- Patients Waiting in Emergency Queue (Front to Rear) ---");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}
