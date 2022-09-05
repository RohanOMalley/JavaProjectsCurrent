
/*
* AUTHOR: Rohan OMalley
* 
* FILE: Library.java
* 
* ASSIGNMENT: Programming Assignment 4 - Spotify
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a Library object. Libraries
* are able to find Song objects from titles, add or remove 
* Song objects to a Library, and can print out the
* descriptions of all the songs in the library alphabetically.
*/

import java.util.Map;
import java.util.TreeMap;

public class Library {
	
	private Map<String, Song> song_Map;
	
	public Library(){
		this.song_Map = new TreeMap<String, Song>();
	}
	
	public Song getSong(String title) {
        /*
         * Method takes in a String of a song title and then
         * method looks to see if that title exists in the 
         * Library object and if it exists the Song object
         * for that title is returned
         * 
         * @param title, String of song title to look up
         * 
         * @return retval, a Song object that matches the title
         * passed in
         * 
         * @return null, if title does not exist in the Library
         */
		if (song_Map.containsKey(title)) {
			Song retval = song_Map.get(title);
			return retval;
		}
		else {
			return null;
		}
	}
	
	public void addSong(Song song) {
        /*
         * Method takes in Song object and adds the object to
         * the song_Map taking the title String from the object
         * and making it the key and the Song the value
         * 
         * @param song, Song object to add to Library
         */
		String key = song.title;
		song_Map.put(key, song);
	}
	
	public void removeSong(Song song) {
        /*
         * Method takes in Song object to remove from
         * the Library by removing key, value pair in the
         * song_Map.
         * 
         * @param song, Song object to remove from Library
         */
		String song_to_remove = song.title;
		song_Map.remove(song_to_remove);
	}

	public String toString() {
        /*
         * Method loops through Library song_Map and builds
         * a string a description of each song in alphabetical
         * order and after every song a newline is added
         * 
         * @return library_String, String of all the song 
         * descriptions in the Library in alphabetical order
         */
		String library_String = "";
		for (String song_title : song_Map.keySet()) {
			Song songObj = song_Map.get(song_title);
			library_String += songObj.toString();
			library_String += "\n";
		}
		return library_String;
		
	}
}
