
/*
* AUTHOR: Rohan OMalley
* 
* FILE: Song.java
* 
* ASSIGNMENT: Programming Assignment 4 - Spotify
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a Song object and all
* that a Song can do. It can show the artist of the song,
* how many times the song has been played, a user can play
* the song and increase the count of plays, Song can also
* print out a full description of the song including song title,
* artist, and how many times played.
*/
public class Song {
	
	public String title;
	public String artist;
	public int played;
	
	public Song(String title, String artist) {
		this.title = title;
		this.artist = artist;
		this.played = 0;
	}
	
	public String getTitle() {
        /*
         * Method returns what the current Song object's song title is
         * 
         * @return this.title, a String of the Song's title
         */
		return this.title;
	}
	
	public String getArtist() {
        /*
         * Method returns what the current Song object's song artist is
         * 
         * @return this.artist, a String of the Song's artist
         */
		return this.artist;
		
	}
	
	public int getTimesPlayed() {
        /*
         * Method returns how many times the current Song object
         * has been played
         * 
         * @return played, an Integer of how many times a song has
         * been played
         */
		return played;
	}
	
	public void play() {
        /*
         * Method prints out the current Song's description including
         * title, artist and how many times played, then increases the
         * count of times played
         */
		System.out.println(this.toString());
		played += 1;
	}
	
	public String toString() {
        /*
         * Method returns String of the current description of the 
         * song.It will look like this "Come Together by The Beatles, 0 play(s)"
         * 
         * @return retval, a String the includes title, artist, and
         * times played.
         */
		String tp = Integer.toString(played);
		String retval = this.title + " by "+ this.artist + ", "+ tp +" play(s)" ;
		return retval;
	}
	
}
