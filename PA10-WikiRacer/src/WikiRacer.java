import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
* AUTHOR: Rohan OMalley
* 
* FILE: WikiRacer.java
* 
* ASSIGNMENT: Programming Assignment 10 - WikiRacer 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program finds the best path
* from the start wiki page to the end page. It 
* goes through all the links on each page and
* ranks the priority of each with the end page
* then will build that path and return it. Also
* creates Ladders and makes their priorities.
*/
public class WikiRacer {

	private static Set<String> endPg;
	/*
	 * Do not edit this main function
	 */
	public static void main(String[] args) {
		List<String> ladder = findWikiLadder(args[0], args[1]);
		System.out.println(ladder);
	}

	/*
	 * Do not edit the method signature/header of this function
	 * TODO: Fill this function in.
	 */
	private static List<String> findWikiLadder(String start, String end) {
		/*
		 * Method find the ladder of wiki pages from
		 * the start page to the end page. Adds Ladders to
		 * the priority queue.
		 * 
		 * @param, String start, link for the starting page
		 * @param, String end, link for the end page
		 * 
		 * @return List retval, list of the path of links from
		 * start to end page
		 */
		// creates empty queue and Ladder with the start page
		// and enqueue Ladder in queue
		MaxPQ ladderQ = new MaxPQ();
		ArrayList<String> firstPage = new ArrayList<String>();
		firstPage.add(start);
		endPg = WikiScraper.findWikiLinks(end);
		ladderQ.enqueue(firstPage, 1);
		
		Set<String> visitedPages = new HashSet<String>();
		
		while (!ladderQ.isEmpty()) {
			// highest priority Ladder
			// is dequeued and the last page in
			// Ladder is pulled and a Set of its Links
			// are created
			Ladder firstLadder = ladderQ.dequeue();
			int ladderLen = firstLadder.ladder.size() - 1;
			String curPage = firstLadder.ladder.get(ladderLen);
			Set<String> curPageSet = WikiScraper.findWikiLinks(curPage);
			
			if (curPageSet.contains(end)) {
				firstLadder.ladder.add(end);
				return firstLadder.ladder;
			}
			else {
				visitedPages.add(curPage);
				curPageSet.parallelStream().forEach(link -> {
					  WikiScraper.findWikiLinks(link);
					});
				for (String link : curPageSet) {
					if (!visitedPages.contains(link)) {
						ArrayList<String> copyLadder = new ArrayList<String>(firstLadder.ladder);
						copyLadder.add(link);
						visitedPages.add(link);
						ladderQ.enqueue(copyLadder, makePriority(link));
					}
				}
			}	
		}
		ArrayList<String> empty = new ArrayList<String>();
		return empty;
	}
	
	
	private static int makePriority (String curPage) {
		/*
		 * Method makes copies of both the curPage
		 * Set and the end Page Set and then takes the
		 * intersection of both and returns the length
		 * of the intersected set. The length is how many
		 * links in common the curPage has with the end page.
		 * 
		 * @param, String curPage, name of the current page
		 * to get the links from
		 * 
		 * @return, int, length of curPageSet which
		 * is how many links in common the curPage has with
		 * the end Page
		 */
		Set<String> curPageSet = WikiScraper.findWikiLinks(curPage);
		Set<String> endPgCopy = new HashSet<String>(endPg);
		curPageSet.retainAll(endPgCopy);
		return curPageSet.size();
	}
	


}
