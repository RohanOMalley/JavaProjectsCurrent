import java.util.ArrayList;
/*
* AUTHOR: Rohan OMalley
* 
* FILE: WikiRacer.java
* 
* ASSIGNMENT: Programming Assignment 10 - WikiRacer 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program creates a Ladder object
* a Ladder is a List of the path from start to end
* page for wiki pages. Ladders can be partially completed
* the priority is determined by the makePriority 
* method in WikiRacer.java. priority can be peeked
* get Ladder returns List of Link names.
*/
public class Ladder {
	
	public ArrayList<String> ladder;
	public int priority;
	
	public Ladder(ArrayList<String> ladder, int priority) {
		this.ladder = ladder;
		this.priority = priority;
	}
	
	public int getPriority() {
		/*
		 * Method returns the int
		 * priority of the Ladder
		 */
		return this.priority;
	}
	
	public ArrayList<String> getLadder(){
		/*
		 * returns the List of links
		 * that make up the Ladder
		 */
		return this.ladder;
	}
	
	
}
