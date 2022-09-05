/*
* AUTHOR: Rohan OMalley
* 
* FILE: WikiScraper.java
* 
* ASSIGNMENT: Programming Assignment 10 - WikiRacer 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program scrapes the HTML from
* a certain wiki link passed in. Then it searches
* through the HTML and finds the links from that page
* and gets a Set of all the links on that page.
* Also has memoization for all the pages visited
* if a page has already been visited the program will
* just return the Set so no extra work has to be done.
*/

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WikiScraper {
			
	static Map<String, Set<String>> visitedSetPages = new HashMap<String, Set<String>>();
	
	public static Set<String> findWikiLinks(String link) {
		/* Method finds the links on the page passed in
		 * if the link passed in exists in the visitedPages
		 * then the Set at that key is returned saving runtime.
		 * If it doesn't exist then the links are found and a
		 * new key, value pair as link to Set of links
		 * 
		 * @param, String link, link name to find all
		 * the links on that wiki page.
		 * 
		 * @return, Set<String>, links from the page
		 */
		if (!visitedSetPages.containsKey(link)) {
			String html = fetchHTML(link);
			Set<String> links = scrapeHTML(html);
			visitedSetPages.put(link, links);
			return links;
		}
		else {
			return visitedSetPages.get(link);
		}

	}
	
	private static String fetchHTML(String link) {
		/*
		 * Method will get the URL from the link
		 * passed in and it gets the all of the 
		 * html and puts it into a string. Then 
		 * string is returned
		 * 
		 * @param, String link, name of link to check
		 * 
		 * @return, String, all html from the page passed in
		 */
		StringBuffer buffer = null;
		try {
			URL url = new URL(getURL(link));
			InputStream is = url.openStream();
			int ptr = 0;
			buffer = new StringBuffer();
			while ((ptr = is.read()) != -1) {
			    buffer.append((char)ptr);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return buffer.toString();
	}

	private static String getURL(String link) {
		/*
		 * Adds the beginning part of the link to the link
		 * passed in so that the fetchHTML function can 
		 * access the web to scrape the HTML.
		 */
		return "https://en.wikipedia.org/wiki/" + link;
	}
	

	private static Set<String> scrapeHTML(String html) {
		/*
		 * Method goes through the html String and searchs for
		 * the pattern in the linkSearch. For every time the pattern
		 * is found the link name is formatted and added to the retval
		 * Set. Then that set is returned.
		 * 
		 * @param, String html, string of all the html code
		 * for a specific page
		 * 
		 * @return, Set<String> links from the html passed in
		 */
		Set<String> retval = new HashSet<String>();
		Matcher linkSearch = Pattern.compile("<a href=\"/wiki/[^:#\"]+\"").matcher(html);
		while (linkSearch.find() == true) {
			 String linkFound = linkSearch.group().substring(15);
			 String trimmedLink = linkFound.substring(0, linkFound.length() - 1);
			 retval.add(trimmedLink);
		}
		return retval;
	}
	
}
