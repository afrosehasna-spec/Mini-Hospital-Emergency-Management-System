/**
 * Binary Search Tree that stores Patient records keyed by Patient ID.
 * Supports insertion, search, deletion, and in-order traversal.
 */
public class PatientBST {

    // Internal node of the BST
    private static class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;
    private int size;

    /** Insert a new patient into the BST using Patient ID as the key. */
    public boolean insert(Patient patient) {
        if (search(patient.getPatientId()) != null) {
            return false; // duplicate ID not allowed
        }
        root = insertRec(root, patient);
        size++;
        return true;
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        }
        return node;
    }

    /** Search for a patient using Patient ID. Returns null if not found. */
    public Patient search(int patientId) {
        Node current = root;
        while (current != null) {
            if (patientId == current.patient.getPatientId()) {
                return current.patient;
            } else if (patientId < current.patient.getPatientId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /** Delete a patient record from the BST by Patient ID. */
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        size--;
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }
        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node with two children: get the in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** In-order traversal: displays patients in ascending order of Patient ID. */
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        System.out.println("--- Patient Records (In-Order by Patient ID) ---");
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.patient);
        inOrderRec(node.right);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
