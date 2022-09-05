
/*
* AUTHOR: Rohan OMalley
* 
* FILE: UserCollection.java
* 
* ASSIGNMENT: Programming Assignment 4 - Spotify
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a User Collection. 
* Can check if a User exists in Collection, can attempt
* a login by checking to see if user exists and if the
* password is correct, can add User to the Collection,
* can print out all the Users and how many playlists they
* have.
*/

import java.util.ArrayList;
import java.util.List;

public class UserCollection {
	
	
	private List<User> userList;
	
	public UserCollection() {
		this.userList = new ArrayList<User>();
	}
	
	public boolean userExists(String username) {
        /*
         * Method checks to see if a User exists
         * by checking the username passed in with
         * the names of each User in the userList
         * 
         * @rparam username, String of name of User to
         * look for
         * 
         * @return true, boolean if username equals a User
         * in the List
         * 
         * @return false, boolean if username does not match
         * any User in the List.
         */
		for (User cur_User : userList) {
			String cur_username = cur_User.getName();
			if(cur_username.equals(username)){
				return true;
			}
		}
		return false;
	}
	
	public User login(String username, String password) {
        /*
         * Method takes in a username and password and
         * checks to see if username matches any Users
         * in the List and then will look to see if that 
         * password matches the User and will return that
         * User if everything matches.
         * 
         * @param username, String of username to look for
         * 
         * @param password, Stringf of password to check against
         * 
         * @return cur_User, User object that matches both
         * username and password
         */
		for (User cur_User : userList) {
			String cur_username = cur_User.getName();
			if(cur_username.equals(username)){
				if (cur_User.attemptLogin(password)) {
					return cur_User;
				}
			}	
		}
		return null;	
	}
	
	public void addUser(User add) {
        /*
         * Method adds User to List of Users
         * 
         * @param add, User to add to List of
         * Users
         */
		userList.add(add);
	}
	
	public String toString() {
        /*
         * Method returns a String of each User and how many
         * Playlists each User has
         * 
         * @return retval, String of each User and how many 
         * playlists they have.
         */
		String retval = "{ ";
		for (User cur_User : userList) {
			String cur_name = cur_User.getName();
			String cur_size = Integer.toString(cur_User.userPlaylists.size());
			
			retval += cur_name +": " +cur_size+ " playlist(s), ";
		}
		retval += "}";
		return retval;
	}
}
