# Mini Hospital Emergency Management System

A console-based Java application built for CIT300 (Data Structures and Algorithms)
that simulates the management of patients arriving at a hospital emergency unit.

## Overview

The system tracks patients from registration through emergency treatment and
keeps a history of both completed treatments and each patient's past visits.
It is a menu-driven console application — run it and follow the on-screen menus.

## Data Structures Used

| Feature | Data Structure | Class |
|---|---|---|
| Patient Records (keyed by Patient ID) | Binary Search Tree | `PatientBST.java` |
| Emergency Patient Queue (FIFO) | Queue (custom linked implementation) | `EmergencyQueue.java` |
| Treatment History (LIFO) | Stack (custom linked implementation) | `TreatmentStack.java` |
| Per-Patient Visit History | Singly Linked List | `VisitLinkedList.java` |

Supporting data classes: `Patient.java`, `Visit.java`, `TreatmentRecord.java`.
Entry point / console menu: `HospitalManagementSystem.java`.

## How Each Structure Is Used

1. **Patient Records — Binary Search Tree**
   Patients are stored in a BST keyed by their Patient ID, which keeps
   insertion, search, and deletion efficient (O(log n) on average) and lets
   the system print patients in ascending ID order with a simple in-order
   traversal.

2. **Emergency Patient Queue — Queue**
   New arrivals are enqueued at the back and the next patient to be seen is
   dequeued from the front, enforcing strict First-In-First-Out treatment
   order.

3. **Treatment History — Stack**
   Every time a patient finishes treatment, a `TreatmentRecord` is pushed
   onto a stack. Popping the stack retrieves the most recently completed
   treatment first (Last-In-First-Out), which is useful for reviewing the
   most recent activity.

4. **Patient Visit History — Singly Linked List**
   Each `Patient` object owns its own `VisitLinkedList`, storing that
   patient's past visits in the order they occurred. Visits can be added,
   removed, searched, and displayed independently for each patient.

## Project Structure

```
hospital-system/
├── README.md
└── src/
    ├── Patient.java
    ├── PatientBST.java
    ├── Visit.java
    ├── VisitLinkedList.java
    ├── EmergencyQueue.java
    ├── TreatmentRecord.java
    ├── TreatmentStack.java
    └── HospitalManagementSystem.java   (main entry point)
```

## How to Compile and Run

```bash
cd src
javac *.java
java HospitalManagementSystem
```

## Sample Usage Flow

1. Add a patient via **Patient Records → Insert New Patient**.
2. Add the same patient to the **Emergency Patient Queue**.
3. **Dequeue** the patient for treatment — this automatically prompts for a
   treatment date and pushes a record onto the **Treatment History** stack.
4. Add a visit entry to that patient under **Patient Visit History**.
5. Explore search, delete, pop, and display operations from each menu.

## Notes on Design Decisions

- The Queue and Stack are implemented from scratch using linked nodes
  (rather than `java.util.Queue`/`java.util.Stack`) to directly demonstrate
  the underlying data structure mechanics, as required by the assignment.
- The BST does not allow duplicate Patient IDs; `insert()` returns `false`
  if the ID already exists.
- Deletion from the BST handles all three cases (no children, one child,
  two children) using the in-order successor approach.
- Each `Patient` instance owns its own `VisitLinkedList`, so visit history
  is naturally scoped per patient rather than kept in one global list.

## Author

Individual assignment submission for CIT300 - Data Structures and Algorithms.
