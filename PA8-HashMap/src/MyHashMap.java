/*
* AUTHOR: Rohan OMalley
* 
* FILE: MyHashMap.java
* 
* ASSIGNMENT: Programming Assignment 6 - MyHashMap
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program defines a replica of
* a HashMap data structure. It has the methods
* clear, containsKey, containsValue, get, isEmpty
* keySet,put, remove, size, printTable. 
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> {
	
	private ArrayList<LinkedList<HashNode<K,V>>> bucketList; 
	private int size;
	
	public MyHashMap() {
		size = 0;
		bucketList = new ArrayList<LinkedList<HashNode<K, V>>>();
		addBuckets();
	}
	
	public void addBuckets () {
        /*
         * Adds 8 buckets to the bucketList
         */
		for(int i = 0; i < 8; i++) {
			bucketList.add(null);
		}
	}
	
	public void clear() {
        /*
         * Clears out the current bucketlist
         * basically starting new
         */
		this.bucketList = new ArrayList<LinkedList<HashNode<K, V>>>();
		addBuckets();
		this.size = 0;
		
	}
	public boolean containsKey(K key) {
        /*
         * @param key, key to check
         * 
         * @return boolean, true if key exists
         * false if otherwise
         */
		int index = hash(key);
		LinkedList<HashNode<K, V>> listIter = bucketList.get(index);
		for (HashNode<K, V> curNode : listIter) {
			if (curNode.getKey().equals(key)) {
				return true;
			}
		}
		return false;
		
	}
	public boolean containsValue(V val) {
        /*
         * @param val, val to check
         * 
         * @return boolean, true if val exists
         * false if otherwise
         */
		for (LinkedList<HashNode<K,V>> bucket : bucketList) {
			System.out.println("WE saw a bucket");
			for (HashNode<K,V> curNode : bucket) {
				if (curNode.getValue().equals(val)) {
					return true;
				}
			}
		}
		return false;
	}
	public V get(K key) {
        /*
         * @param key, value to get from map
         * 
         * @return retval, value mapped to key passed in
         */
		int index = hash(key);
		
		LinkedList<HashNode<K, V>> listIter = bucketList.get(index);
		for (HashNode<K, V> curNode : listIter) {
			if (curNode.getKey().equals(key)) {
				V retval = curNode.getValue();
				return retval;
			}
		}
		return null;
	}
	public boolean isEmpty() {
        /*
         * checks if map is empty
         * 
         * @return boolean, true if empty
         * false is otherwise
         */
		for (LinkedList<HashNode<K,V>> bucket : bucketList) {
			if (bucket != null) {
				return false;
			}
		}
		return true;
		
	}
	public Set<K> keySet() {
        /*
         * returns a set of all the keys
         * in the map
         * 
         */
		Set<K> retval = new HashSet<K>();
		for (LinkedList<HashNode<K,V>> bucket : bucketList) {
			for (HashNode<K,V> curNode : bucket) {
				retval.add(curNode.getKey());
			}
		}
		return retval;
		
	}
	public V put(K newKey,V newVal) {
        /*
         * puts a new value and key pair into the map
         * replaces the value if key already exists
         * 
         * @return value from old pair if key exists,
         * null if no key existed
         */
		int index = hash(newKey);
		int len = bucketList.size();
		LinkedList<HashNode<K,V>> listPut = bucketList.get(index);
		HashNode<K,V> addNode = new HashNode<K,V>(newKey, newVal);
		if (listPut == null) {
			listPut = new LinkedList<HashNode<K,V>>();
			bucketList.set(index, listPut);
			listPut.addFirst(addNode);
			size++;
			return null;
		}
		else {
			for (HashNode<K, V> curNode : listPut) {
				if (curNode.getKey().equals(newKey)){
					V retval = curNode.getValue();
					curNode.setValue(newVal);
					return retval;
				}
			}
			listPut.addFirst(addNode);
			size++;
			return null;
		}
		
		
		
	}
	public V remove(K keyToRemove) {
        /*
         * removes value mapped to the key passed in
         * 
         * @return value removed, null if no key present
         */
		int index = hash(keyToRemove);
		int curIndex = 0;
		for (LinkedList<HashNode<K,V>> bigList: bucketList) {
			if (curIndex == index) {
				for (HashNode<K, V> curNode : bigList) {
					if (curNode.getKey().equals(keyToRemove)) {
						V retval = curNode.getValue();
						bigList.remove(curNode);
						return retval;
					}
				}
			}
			else {
				curIndex++;
			}
		}
		return null;

	}
	public int size () {
        /*
         * returns size of all keys in map
         */
		return size;
		
	}
	public void printTable() {
        /*
         * prints out each bucket with how many
         * conflicts in each
         */
		String[] keyArr = new String[8];
		int curBucket = 0;
		
		for(LinkedList<HashNode<K,V>> bucket : bucketList) {
			String bucketString = "[";
			if (bucket == null) {
				keyArr[curBucket] = "[]";
				curBucket++;
			}
			else {
				for (HashNode<K, V> curNode : bucket) {
					bucketString += curNode.getKey().toString() + ", ";
				}
				keyArr[curBucket] = bucketString;
				curBucket++;
			}
		}
		curBucket = 0;
		for (LinkedList<HashNode<K,V>> bucket : bucketList) {
			if (bucket == null) {
				System.out.println("Index " + Integer.toString(curBucket) + ": (0 conflicts), "
			+ keyArr[curBucket]);
				curBucket++;
			}
			else {
				int conflictSize = bucket.size() - 1;
				System.out.println("Index" + Integer.toString(curBucket) + ": (" +
				Integer.toString(conflictSize) + " conflicts), "+ keyArr[curBucket]);
				curBucket++;
			}

		}
	}
	
	int numBuckets = 8;
	private int hash(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}

}
