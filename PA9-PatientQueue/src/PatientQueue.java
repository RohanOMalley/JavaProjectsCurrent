/*
* AUTHOR: Rohan OMalley
* 
* FILE: PatientQueue.java
* 
* ASSIGNMENT: Programming Assignment 9 - PatientQueue 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program creates a PatientQueue
* data structure. It is a priority queue that holds
* Patient objects based on the Patient class. The 
* structure behind the queue is a minimum binary
* heap. The Patient Queue has the methods: enqueue,
* dequeue, peek, peekPriority, changePriority,
* isEmpty, clear, and toString.
*/
public class PatientQueue {
    private Patient[] heap;
    private int size;

    public PatientQueue() {
        this.heap = new Patient[11];
        this.size = 1;
    }

    public void growArray() {
        /*
         * Method is called when the current heap
         * array is too small so the array size is
         * doubled and all the elements are copied into
         * the new bigger array. Then this array is set to
         * the heap array
         */
        Patient[] newHeap = new Patient[2 * heap.length];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    public void enqueue(String name, int priority) {
        /*
         * Method creates a Patient object with the
         * name and priority passed in. Then the Patient
         * is added to the heap and will bubble up to 
         * where they should be in the line based on
         * their priority
         * 
         * @param String name, name of Patient to be added
         * @param int priority, number of what priority Patient is
         */
        if (heap.length == size) {
            growArray();
        }
        Patient patientToAdd = new Patient(name, priority);
        heap[size] = patientToAdd;
        if (size != 1) {
            bubbleUp(size);
        }
        size++;

    }

    public void enqueue(Patient patient) {
        /*
         * enqueues a Patient object by calling the
         * enqueue method and taking the name and priority
         * from the passed in patient and passing it
         * in the enqueue function
         * 
         * @param patient, Patient object to add to queue
         */
        enqueue(patient.name, patient.priority);
    }

    private void bubbleUp(int start) {
        /*
         * Method takes in a start index and
         * bubbles up through the queue based on the
         * if the parent priority is greater than the
         * current index.
         * 
         * @param start, integer to start at in
         * the queue
         */
        int curIndex = start;
        
        while (curIndex > 1) {
            Patient child = heap[curIndex];
            Patient parent = heap[curIndex / 2];
            if (parent.priority > child.priority) {
                Patient temp = heap[curIndex];
                heap[curIndex] = heap[curIndex / 2];
                heap[curIndex / 2] = temp;
                curIndex = curIndex / 2;
            }
            else {
                curIndex = curIndex / 2;
            }
        }
    }

    public String dequeue() throws NullPointerException {
        /*
         * Method takes off the Patient
         * that is first in line in the Queue.
         * 
         * @return String retval, the name of the Patient that
         * was front of the line
         */
        String retval = heap[1].name;
        heap[1] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        bubbleDown(1);
        return retval;


    }

    private void bubbleDown(int startIndex) {
        /*
         * Method takes in a index to start
         * bubbling down on the list. Loops
         * through the queue and bubbles down
         * with most urgent child
         * 
         * @param startIndex, index to start at
         */
        int curIndex = startIndex;


        while (curIndex * 2 <= this.size) {
            Patient pFront = heap[curIndex];
            Patient leftChild = heap[curIndex * 2];
            Patient rightChild = heap[curIndex * 2 + 1];
            Patient smallChild = leftChild;

            if (rightChild.priority <= this.size
                    && leftChild.priority > rightChild.priority) {
                smallChild = rightChild;
            }

            if (pFront.priority > smallChild.priority) {
                Patient temp = heap[curIndex];
                heap[curIndex] = heap[curIndex * 2];
                heap[curIndex * 2] = temp;
            } else {
                break;
            }
            curIndex = smallChild.priority;
        }
    }

    public String peek() throws NullPointerException {
        /*
         * Mehtod looks at what Patient is first
         * in the Queue. Returns what their name is.
         * Does not modify the Queue.
         * 
         * @return String retval, name of first Patient in
         * line
         */
        Patient firstPatient = heap[1];
        return firstPatient.name;

    }

    public int peekPriority() throws NullPointerException {
        /*
         * Mehtod looks at what Patient is first
         * in the Queue. Returns what their priority number is.
         * Does not modify the Queue.
         * 
         * @return int retval, number of Patient infront of
         * the Queue
         */
        Patient firstPatient = heap[1];
        return firstPatient.priority;
    }

    public void changePriority(String name, int newPriority) {
        /*
         * Method changes the priority of the name of
         * the Patient passed in. Check the Queue to see if
         * the Patient exists and if so their priority is set
         * the the newPriority passed in. Then bubbling occurs to
         * get Patient in right spot in Queue.
         * 
         * @param String name, name of Patient to change
         * 
         * @param int newPriority, number that will be the new
         * priority of Patient
         */
        
        for (int start = 1; start < size; start++) {
            if (heap[start].name == name) {
                if (start * 2 < size - 1) {
                    if (heap[start * 2].priority > newPriority) {
                        heap[start].priority = newPriority;
                        bubbleDown(start);
                    }
                }
                if (start / 2 > 0) {
                    if (heap[start / 2].priority > newPriority) {
                        heap[start].priority = newPriority;
                        bubbleUp(start);
                    }
                }
                break;
            }
        }

    }

    public boolean isEmpty() {
        /*
         * Checks the length of the heap
         * to see if it is empty. If so true is
         * returned, false if otherwise
         */
        return size - 1 == 0;
    }

    public int size() {
        /*
         * returns the current amount of elements
         * in the queue
         */
        return size - 1;
    }

    public void clear() {
        /*
         * Method clears out the Queue.
         * Starts the Queue off fresh
         */
        size = 0;
        heap = new Patient[11];
    }

    @Override
    public String toString() {
        /*
         * Method prints out the current status of the
         * Queue with Patients name and their priorities
         * 
         * In this format:
         * "{Anat (4), Rein (6), Sasha (8), Ben (9), Wu (7), Eve (10)}"
         * 
         * @return String retval, patient names and priorities in format
         * above
         */
        String retval = "{";
        for (int i = 0; i < heap.length; i++) {
            Patient curPatient = heap[i];
            if (curPatient != null) {
                if (i == size - 1) {
                    retval += curPatient.toString();
                } else {
                    retval += curPatient.toString() + ", ";
                }
            }
        }
        retval += "}";
        return retval;
    }

}
