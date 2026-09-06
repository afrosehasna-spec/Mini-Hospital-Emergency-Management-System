# Mini Hospital Emergency Management System

A console-based Java application developed for **CIT300 – Data Structures and Algorithms**. The system simulates the management of patients in a hospital emergency unit using different data structures.

## Overview

The Mini Hospital Emergency Management System manages patients from registration through emergency treatment and maintains records of completed treatments and previous hospital visits.

The application is menu-driven and runs through the Java console.

## Data Structures Used

| Feature                 | Data Structure     | Class                  |
| ----------------------- | ------------------ | ---------------------- |
| Patient Records         | Binary Search Tree | `PatientBST.java`      |
| Emergency Patient Queue | Queue              | `EmergencyQueue.java`  |
| Treatment History       | Stack              | `TreatmentStack.java`  |
| Patient Visit History   | Singly Linked List | `VisitLinkedList.java` |

Supporting classes:

* `Patient.java`
* `Visit.java`
* `TreatmentRecord.java`
* `HospitalManagementSystem.java` – Main program and menu

## How Each Data Structure Is Used

### 1. Patient Records – Binary Search Tree

Patients are stored in a **Binary Search Tree (BST)** using the Patient ID as the key.

The BST supports:

* Inserting a new patient
* Searching for a patient by Patient ID
* Deleting a patient
* Displaying patients in ascending Patient ID order using in-order traversal

The BST provides **O(log n) average-case** search, insertion, and deletion when the tree is reasonably balanced.

### 2. Emergency Patient Queue – Queue

The emergency queue follows the **FIFO (First-In-First-Out)** principle.

* New patients are added to the rear of the queue.
* The next patient is removed from the front.
* The first patient who enters the queue is the first patient to be treated.

The queue is implemented using linked nodes instead of Java's built-in queue classes.

### 3. Treatment History – Stack

The treatment history follows the **LIFO (Last-In-First-Out)** principle.

When a patient completes treatment, a `TreatmentRecord` is pushed onto the stack.

The most recently completed treatment can then be retrieved first using the `pop()` operation.

### 4. Patient Visit History – Singly Linked List

Each patient has their own **Singly Linked List** for storing previous hospital visits.

Each visit contains:

* Visit ID
* Date
* Doctor
* Diagnosis
* Treatment

The system supports adding, removing, searching, and displaying visit records.

## Main Features

### Patient Records

* Insert new patient
* Search patient by ID
* Delete patient
* Display patients in ascending ID order

### Emergency Patient Queue

* Enqueue patient
* Dequeue patient
* Display waiting patients
* Handle an empty queue

### Treatment History

* Push completed treatment
* Pop the most recent treatment
* Display treatment records
* Handle an empty stack

### Patient Visit History

* Add a visit
* Remove a visit
* Search for a visit
* Display a patient's visit history
* Handle patients with no visit history

## Project Structure

```text
Mini-Hospital-Emergency-Management-System/
│
├── .gitignore
├── README.md
├── EmergencyQueue.java
├── HospitalManagementSystem.java
├── Patient.java
├── PatientBST.java
├── TreatmentRecord.java
├── TreatmentStack.java
├── Visit.java
└── VisitLinkedList.java
```

## How to Compile and Run

Open a terminal in the project folder and run:

```bash
javac *.java
```

Then start the application:

```bash
java HospitalManagementSystem
```

## Sample Usage Flow

1. Select **Patient Records** from the main menu.
2. Insert a new patient.
3. Search or display patient records.
4. Add the patient to the **Emergency Patient Queue**.
5. Dequeue the patient when treatment is provided.
6. Add the completed treatment to the **Treatment History**.
7. Add previous or new visit details under **Patient Visit History**.
8. Search, remove, or display visit records when required.

## Design Decisions

* The Queue and Stack are implemented from scratch using linked nodes instead of Java's built-in collection classes.
* The BST uses Patient ID as the key and does not allow duplicate Patient IDs.
* BST deletion handles nodes with no children, one child, and two children.
* The in-order traversal of the BST displays patients in ascending Patient ID order.
* Each patient owns a separate `VisitLinkedList`, keeping visit history specific to that patient.
* Empty Queue, Stack, and Visit History cases are handled with appropriate messages.

## Technologies Used

* **Java**
* **Object-Oriented Programming**
* **Binary Search Tree**
* **Queue**
* **Stack**
* **Singly Linked List**
* **Git & GitHub**

## Author

**M. Afrose Hasna**
**23DA2-0610**

Individual Assignment – CIT300
**Data Structures and Algorithms**
