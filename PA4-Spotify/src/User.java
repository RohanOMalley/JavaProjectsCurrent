
/*
* AUTHOR: Rohan OMalley
* 
* FILE: User.java
* 
* ASSIGNMENT: Programming Assignment 4 - Spotify
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a User object. User object
* can return the name of the User. User can attempt to login
* to account, User can add playlists,then can return a List
* of Playlist objects. Can pick a playlist and then play all
* the songs in it, can print out a list of all the User's
* playlists
* 
*/

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private String user_Password;
	public List<Playlist> userPlaylists;
	
	public User(String name, String password) {
		this.name = name;
		this.user_Password = password;
		this.userPlaylists = new ArrayList<Playlist>();
	}
	
	public String getName() {
        /*
         * Method returns the name of the current 
         * User object
         * 
         * @return name, String of name of current User
         */
		return name;
	}
	
	public boolean attemptLogin(String password) {
        /*
         * Method takes in a password and checks to see
         * if the password equals the User password and 
         * returns true if they are equal
         * 
         * @param password, String of a password
         * 
         * @return true, boolean that passwords match
         * 
         * @return false, boolean that passwords don't match
         */
		if (password.equals(user_Password)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addPlaylist(Playlist newPlaylist){
        /*
         * Method takes in a Playlist object that will
         * be added to the total List of Playlists that
         * the User has
         * 
         * @param newPlaylist, Playlist object to be added
         * to List of User Playlists
         * 
         */
		userPlaylists.add(newPlaylist);
	}
	
	public List<Playlist> getPlaylists(){
        /*
         * Method returns the List of User Playlists
         * 
         * @return userPlaylists, List of Playlists
         * that the User has
         */
		return userPlaylists;
	}
	
	public void selectPlaylist(String name) {
        /*
         * Method takes in a name of a playlist and then 
         * loops through the userPlaylists to find a playlist
         * with the same name as passed in then plays all
         * the songs in the playlist.
         * 
         * @param name, String of name of Playlist object
         * 
         */
		for (Playlist cur_Playlist : userPlaylists) {
			String cur_name = cur_Playlist.getName();
			if (cur_name.equals(name)) {
				cur_Playlist.play();
			}
		}
	}
	
	public String toString() {
        /*
         * Method returns a String of how many playlists a 
         * User has
         * 
         * @return retval, String detailing how many playlists
         * a User has
         */
		String plLen = Integer.toString(userPlaylists.size());
		String retval = name + ", " + plLen + " playlist(s)";
		return retval;
		}
		

}
