package mainpack;

public class ArrayQueue {
	
	private static final int DEFAULT_SIZE = 100;
	private int[] array;
	private int size;
	
	public ArrayQueue() {
		array = new int[DEFAULT_SIZE];
		size = 0;
	}
	
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        int[] newArray = new int[2 * array.length];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void add(int value) {
        if (size >= array.length) {
            growArray();
        }
        array[size + 1] = value;
        size++;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        size = 0;
    }

    public int get(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index must be less than size.");
        }
        return array[index];
    }

    public int set(int index, int element) {
        if (index >= size) {
            throw new IllegalArgumentException("Index must be less than size.");
        }
        int value = array[index];
        array[index] = element;
        return value;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int remove(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Index must be less than size.");
        }
        int result = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return result;
    }

    @Override
    public String toString() {
        String result = "{ ";
        for (int i = 0; i < size; i++) {
            result += array[i] + ", ";
        }
        result += "}";
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayQueue) {
        	ArrayQueue arrQueue = (ArrayQueue) o;
            if (arrQueue.size != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (arrQueue.array[i] != array[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
	
	
	
}
