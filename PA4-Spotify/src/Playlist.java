
/*
* AUTHOR: Rohan OMalley
* 
* FILE: Playlist.java
* 
* ASSIGNMENT: Programming Assignment 4 - Spotify
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a Playlist object. A Playlist
* can create a new Playlist with a List of songs passed in, 
* it can get return the name of the playlist, add Song objects
* to the playlist, play all the songs in the playlist,and can
* remove Song objects from the playlist.
*/

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	private String name;
	private List<Song> songList;
	
	public Playlist(String name) {
		this.name = name;
		this.songList = new ArrayList<Song>();
	}
	
	public Playlist(String name, List<Song> contents) {
        /*
         * Method takes in a name of a playlist to create and
         * a List of songs to add to the playlist. Method then
         * creates new Playlist object then adds each song 
         * from the List to the Playlist object we created
         * 
         * @param name, String of what to name Playlist 
         * 
         * @param contents, List of Song objects to add to 
         * the Playlist we create
         */
		Playlist curPlaylist = new Playlist(name);
		for ( Song song_obj : contents) {
			curPlaylist.addSong(song_obj);
		}
	}
	
	public String getName() {
        /*
         * Method returns the current name of the Playlist
         * 
         * @return name, String of the name of the Playlist
         */
		return name;
	}
	
	public void addSong(Song song) {
        /*
         * Method takes in a Song object and adds it
         * to the List of Songs called songList
         * 
         * @param song, Song object wanted to be added
         */
		if (song != null) {
			songList.add(song);
		}
	}
	
	public void play() {
        /*
         * Method loops through the Song objects in the
         * Playlist's Song List and plays each one which
         * prints out a description of the song and then
         * increases the count of times played.
         */
		for(Song curSong : songList) {
			curSong.play();
		}
	}
	
	public void removeSong(Song song) {
        /*
         * Method takes in a Song object checks to see if 
         * the Song exists in the List then removes it from
         * the Playlist's Song List.
         * 
         * @param song, Song object to be removed from Playlist
         */
		if (songList.contains(song)) {
			int indextoRemove = songList.indexOf(song);
			songList.remove(indextoRemove);
		}
	}
}
