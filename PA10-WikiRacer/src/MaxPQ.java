import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
* AUTHOR: Rohan OMalley
* 
* FILE: MaxPQ.java
* 
* ASSIGNMENT: Programming Assignment 10 - WikiRacer 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program creates a MaxPQueue
* data structure. It is a priority queue that holds
* Ladder objects based on the Ladder class. The 
* structure behind the queue is a maximum binary
* heap. The Ladder Queue has the methods: enqueue,
* dequeue, peek, peekPriority, changePriority,
* isEmpty, clear
*/
public class MaxPQ {
    private Ladder[] heap;
    private int size;
    
    public MaxPQ() {
    	size = 1;
    	heap = new Ladder[100];
    }
	
    public void growArray() {
        /*
         * Method is called when the current heap
         * array is too small so the array size is
         * doubled and all the elements are copied into
         * the new bigger array. Then this array is set to
         * the heap array
         */
        Ladder[] newHeap = new Ladder[2 * heap.length];
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
    
    public void enqueue(ArrayList<String> newLadder, int priority) {
        /*
         * Method creates a Ladder object with the
         * name and priority passed in. Then the Ladder
         * is added to the heap and will bubble up to 
         * where they should be in the line based on
         * their priority
         * 
         * @param ArrayList newLadder, list of links in the
         * ladder.
         * @param int priority, number of what priority Ladder is
         */
    	
        if (heap.length == size) {
            growArray();
        }
        
        Ladder addLadder = new Ladder(newLadder, priority);
        heap[size] = addLadder;
        if (size != 1) {
            bubbleUp(size);
        }
        size++;
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
            Ladder child = heap[curIndex];
            Ladder parent = heap[curIndex / 2];
            if (parent.priority < child.priority) {
                Ladder temp = heap[curIndex];
                heap[curIndex] = heap[curIndex / 2];
                heap[curIndex / 2] = temp;
                curIndex = curIndex / 2;
            }
            else {
                curIndex = curIndex / 2;
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
    
    public Ladder dequeue() throws NullPointerException {
        /*
         * Method takes off the Ladder with the highest
         * priority that is at the front of the queue
         * 
         * @return Ladder retval, the Ladder that
         * was front of the line
         */
        Ladder retval = heap[1];
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
            Ladder pFront = heap[curIndex];
            Ladder leftChild = heap[curIndex * 2];
            Ladder rightChild = heap[curIndex * 2 + 1];
            Ladder bigChild = leftChild;
            
            if (rightChild == null || leftChild == null) {
            	break;
            }
            else {
                if (rightChild.priority <= this.size
                        && leftChild.priority < rightChild.priority) {
                    bigChild = rightChild;
                }
                if (pFront.priority < bigChild.priority) {
                    Ladder temp = heap[curIndex];
                    heap[curIndex] = heap[curIndex * 2];
                    heap[curIndex * 2] = temp;
                } 
                else {
                    break;
                }
                curIndex = bigChild.priority;
            }
        }
    }	
}
