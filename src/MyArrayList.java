/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <
 * Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 * >
 * Signed,
 * Author: NEIRA IBRAHIMOVIC
 * Date: <2025-01-20>
 */

public class MyArrayList<E> {

    /*
     * Do not change this initial capacity; it is used by our test cases
     */
    private static final int INITIAL_CAPACITY = 4;

    /*
     * These are protected so that test cases can access them. Please do not change
     * the visibility of these fields!
     */
    protected Object[] data;
    protected int size = 0;

    // Default constructor with no argument
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    /*
     * The requirements for this constructor are in 
     * Create Constructor Section in the assignment pdf
     * Constructor to initialize the underlying array
     */
    public MyArrayList(E[] arr) {
        // If the argument is null or has length 0, then the constructor must behave like the default constructor
    	if (arr == null || arr.length == 0) {
            // Behave like the default constructor
            data = new Object[INITIAL_CAPACITY];
            size = 0;
        } else {
        	// Set size of MyArrayList equal to the length of the input array
        	size = arr.length;
        	
        	// Ensure the length of the MyArrayList underlying array is either the length of the input array or the value of INITIAL_CAPCITY, whichever is greater.
        	// Determine which one is greater
        	int capacity = Math.max(arr.length, INITIAL_CAPACITY);
        	
        	// Create a new array with the determined capacity
        	data = new Object[capacity];
        	
        	// Copy elements from the input array to the underlying array
        	System.arraycopy(arr, 0, data, 0, size);
        }
    }

    // Get value of array at specified index
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else
            return (E)data[index]; // Cast Object to E
    }

    // Increase the capacity of the array
    private void increaseCapacity() {
        // Object storage supports every generic element type during growth.
        Object[] newData = new Object[Math.max(2 * data.length, INITIAL_CAPACITY)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    /*
     * This method adds the element to the list. Except for modifying it to use Java
     * Generics, DO NOT OTHERWISE CHANGE THIS METHOD as it is used in testing your
     * code.
     */
    
    // Add a value to the end of the array
    public void add(E value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size++] = value; //First add the value to the end with data[size], then increment the size by 1
    }

    // Insert a value at a specified index
    public void add(int index, E element) {
    	// If the index is out of bounds, throw an exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // If the array is full, increase the capacity
        if (size == data.length) {
            increaseCapacity();
        }
        // For all values after the specified index, shift them over one index
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        // Put the new element at the specified index location
        data[index] = element;
        // Increase the size of the array to account for the additional element
        size++;
    }

    // Remove a value from a specified index
    public E remove(int index) {
    	// If the index is out of bounds, throw an exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // Locate the target element to remove at the specified index and store in variable
        E target = (E)data[index];
        // Shift all elements after the one specified at the index back a spot
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        // Reduce the size of the array to account for the removed element
        size--;
        // Ensure the last value is null
        data[size] = null;
        
        // Check if the array needs to be shrunk by using the helper method
        shrinkArrayIfNecessary();
        
        // Return the value that was removed
        return target;
    }
    
    
    /*
     * This method halves the length of the underlying array after removing an element
     * when the new size of the underlying array is less than or equal to 25% of the length of the underlying array
     */
    private void shrinkArrayIfNecessary() {
        // Check if the size is less than or equal to 25% of the current capacity
        if (size <= data.length / 4) {
            // Shrink the array to half its current length
            int newLength = data.length / 2;
            Object[] newData = new Object[newLength];

            // Copy the existing elements into the new smaller array
            System.arraycopy(data, 0, newData, 0, size);

            // Update the reference to the new array
            data = newData;
        }
    }

    /*
     * The requirements for this method are in 
     * remove method Section in the assignment pdf
     * Remove the first instance of the specified object from the array if it exists and return true. Otherwise return false.
     */
    public boolean remove(E obj) {
    	// Check if the array contains the specified object
    	boolean containsObject = this.contains(obj);
    	
    	// If the array doesn't contain the object, return false
    	if (containsObject == false) {
    		return false;
    	// Otherwise, use the indexOf method to find the first occurrence of the object in the array
    	} else {
    		int index = this.indexOf(obj);
    		
    		// Use the remove(int index) method to remove the element at the found index
    	    this.remove(index);

    	    // Return true since the object was found and removed
    	    return true;
    	}
    }

    // Print the array's values
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + data[i]);
        }
    }

    // Check if the array contains the specified object
    public boolean contains(E obj) {
        for (int i = 0; i < size; i++) {
            if (obj == data[i] || (data[i] != null && data[i].equals(obj)))
                return true;
        }
        return false;
    }

    /*
     * The requirements for this method are in 
     * set(index, value) method Section in the assignment pdf
     */
    public E set(int index, E obj) {
       	// If the index is out of bounds, throw an exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
            
        // Store the element at the specified index in a variable
           E elementToReplace = (E)data[index];
           
        // Replace the existing value at the specified index with the new value
           data[index] = obj;
    	
        // Return the element that was replaced
           return elementToReplace;
    }
    
    /*
     * The requirements for this method are in
     * indexOf method Section in the assignment pdf
     */
    public int indexOf(E obj) {
    	// Iterate through every element in the array
    	for (int i = 0; i < size; i++) {
    		// Compare the arrayItem with the specified object
            if (data[i] == obj || (data[i] != null && data[i].equals(obj))) { 
                // If the arrayItem matches the object, return the index
            	return i;
            	}
    		}
        // If no matching element is found, return -1
        return -1;
    	}
}

