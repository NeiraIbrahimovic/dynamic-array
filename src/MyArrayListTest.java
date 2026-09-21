
/**
 * This JUnit test file is a refresher on how to write Java unit tests for your homework. 
 * Please fulfill the unfinished test cases marked with[[TODO]].
 *
 * Using this file is optional, but it is highly recommended. 
 * You can use it to test your code as you are working on it:
 *
 * 1. Feel free to add additional tests as you need. This file covers many of the cases documented in the homework writeup, 
 * but it is not guaranteed to cover everything.
 *
 * 2. You should fully test your code before submitting it to the autograder. 
 *
 * 3. If you fail an autograder test, you should add a test to this file to catch the failure. 
 * Then fix your code to pass the test before resubmitting.
 *
 */
import java.lang.reflect.TypeVariable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * Overview of the test cases for MyArrayList.java:
 * 1. Annotation before test cases with @: 
 *		* A method with annotation @Test indicates the method is a junit test case
 * 		* @Timeout defines a timeout value for the test case. It will throw TimeoutExaception if the test case runs longer than value (in seconds)
 *
 * 2. There are six test blocks of this test cases:
 *     * Step 1 - Test Generic set up
 *     * Step 2 - Test remove method
 *     * Step 3 - Test shrinking the array when it is too large
 *     * Step 4 - Test set method
 *     * Step 5 - Test indexOf method
 *     * Step 6 - Test Create Constructor to Initialize Underlying Array
 */


public class MyArrayListTest {

    /**
     *  Step 1 - Test Generic set up
     */

    private final Class<?> cls = MyArrayList.class;
    private final TypeVariable<? extends Class<?>> typeVar = cls.getTypeParameters()[0];

    // Test that the MyArrayClass has a field called data of type Object array
    @Test
    @Timeout(value = 10)
    public void testDataField() {
        try {
            // Object[].class is an array of the Object class.  The data type in MyArrayList should also be an array of the Object class
            assertEquals(Object[].class, cls.getDeclaredField("data").getGenericType(), "MyArrayList 'data' field has incorrect type");
        } catch (NoSuchFieldException e) {
            fail("MyArrayList does not have field 'data'");
        }
    }

    /* Test that a MyArrayList Class has a constructor that takes an Object array
       Note that this is not implemented until step 5 in the homework */
    @Test
    @Timeout(value = 10)
    public void testGenericConstructor() {
        try {
            /* There should be a MyArrayList constructor that takes one Object array parameter
               This parameter must be the generic parameter E[] */
            if (cls.getConstructor(Object[].class).getGenericParameterTypes()[0] == Object[].class)
                fail("MyArrayList has incorrect constructor parameter type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have constructor of one generic argument");
        }
    }

    /*
     * Test that the method [ add(value) ] takes a generic parameter and has a void return
     * Note that in the starter code there are two methods with the name "add", and here we are referring
     * to the "add" method that takes exactly one parameter.
     */
    @Test
    @Timeout(value = 10)
    public void testGenericAddMethod() {
        try {
            var addMethod = cls.getMethod("add", Object.class);
            /* The return type of the add method should be the void class
               The input parameter should be the generic type E */
            assertEquals(void.class, addMethod.getReturnType(), "MyArrayList add method has incorrect return type (should be void)");
            assertEquals(typeVar, addMethod.getGenericParameterTypes()[0], "MyArrayList add method has incorrect parameter type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have add method that takes generic parameter");
        }
    }

    /*
     * Test that the method [ add(index, element) ] takes an int and generic parameter and returns void
     * Note that in the starter code there are two methods with the name "add", and here we are referring
     * to the "add" method that takes two parameters.
     */
    @Test
    @Timeout(value = 10)
    public void testGenericAddIndexMethod() {
        try {
            var addIndexMethod = cls.getMethod("add", int.class, Object.class);
            /* The return type of the addIndex method should be the void class
               The first input parameter should be the int class
               The second input parameter should be the generic type E */

            // Added during portfolio maintenance: verify the stated contract.
            assertEquals(int.class, addIndexMethod.getGenericParameterTypes()[0]);
            assertEquals(typeVar, addIndexMethod.getGenericParameterTypes()[1]);
            assertEquals(void.class, addIndexMethod.getReturnType());

            // TODO : Add an assert statement to verify the first parameter is of type int

            // TODO : Add an assert statement to verify the second parameter is Generic (see unit test for add method)

            // TODO : Add an assert statement to verify the return type is void (see unit test for add method)

        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have add method that takes int index and a generic obj as parameters");
        }
    }

    // Test that contains method takes a generic parameter and returns a boolean
    @Test
    @Timeout(value = 10)
    public void testGenericContainsMethod() {
        try {
            var containsMethod = cls.getMethod("contains", Object.class);
            /* The return type of the contains method should the boolean class
               The input parameter should be the generic type E */
            assertEquals(boolean.class, containsMethod.getReturnType(), "MyArrayList contains method has incorrect return type (should be boolean)");
            assertEquals(typeVar, containsMethod.getGenericParameterTypes()[0], "MyArrayList contains method has incorrect parameter type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have a contains method with generic parameter");
        }
    }

    // Test that get method takes an int parameter and returns a generic
    @Test
    @Timeout(value = 10)
    public void testGenericGetMethod() {
        try {
            /* The return type of the get method should be the generic type E
               The input parameter should be of type int */
            assertEquals(typeVar, cls.getMethod("get", int.class).getGenericReturnType(), "MyArrayList get method has incorrect return type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have a get method with int parameter");
        }
    }

    /* There are two remove methods, one takes an int parameter, the other takes an object
       This test checks the remove method that has the int parameter */
    @Test
    @Timeout(value = 10)
    public void testGenericRemoveMethod() {
        try {
            /*
            The return type of the get method should be the generic type E
            The input parameter should be of type int
            */
            assertEquals(typeVar, cls.getMethod("remove", int.class).getGenericReturnType(), "MyArrayList remove by index method has incorrect return type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have remove by index method");
        }
    }

    /* There are two remove methods, one takes an int parameter, the other takes an object
       This test checks the remove method that has the object parameter */
    @Test
    @Timeout(value = 10)
    public void testGenericRemoveObjMethod() {
        try {
            var removeObjMethod = cls.getMethod("remove", Object.class);
            /* The return type of the remove method should be the boolean class (true if the input object was present in the array)
               The input parameter should be of type object  */

            // Added during portfolio maintenance: verify the stated contract.
            assertEquals(boolean.class, removeObjMethod.getReturnType());
            assertEquals(typeVar, removeObjMethod.getGenericParameterTypes()[0]);

            // TODO : Add an assert statement that checks the return value of this method (boolean)

            // TODO : Add an assert statement that checks that the first parameter is generic type

        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have remove object method with generic parameter");
        }
    }

    // Test that set method takes an int and object parameter and returns a generic type
    @Test
    @Timeout(value = 10)
    public void testGenericSetMethod() {
        try {
            var setMethod = cls.getMethod("set", int.class, Object.class);
            /* The return type of the set method should be generic type E
               The set method should take an int class parameter and an object parameter */
            assertEquals(typeVar, setMethod.getGenericReturnType(), "MyArrayList set method has incorrect return type");
            assertEquals(int.class, setMethod.getGenericParameterTypes()[0], "MyArrayList set method has incorrect parameter type");
            assertEquals(typeVar, setMethod.getGenericParameterTypes()[1], "MyArrayList set method has incorrect parameter type");
        } catch (NoSuchMethodException e) {
            fail("MyArrayList does not have set method that takes int index and a generic obj as parameters");
        }
    }

    // Test that the MyArrayList class works with parameter Double
    @Test
    @Timeout(value = 10)
    public void testGenericUse() {
        MyArrayList<Double> list = new MyArrayList<>();
        Double testValue = 3.14;
        list.add(testValue);
        // MyArrayList should be able to take in a Double as a parameter and return the correct value
        assertEquals(testValue, list.get(0), "MyArrayList generic add and get test failed");
        assertEquals(Double.class, list.get(0).getClass(), "MyArrayList generic add incorrect return type");
    }

    /**
     *  Step 2 - Test remove method
     */

    // Test basic functionality of remove method when element to remove exists
    @Test
    @Timeout(value = 10)
    public void testRemoveWhenElementExists() {
        String[] arr = new String[] {"u", "v", "w", "x", "y", "z"};
        MyArrayList<String> list = new MyArrayList<>();
        for (String e: arr) {
            list.add(e);
        }
        try {
            int originalSize = list.size;
            boolean result = list.remove("w");
            assertTrue(result, "MyArrayList.remove returns incorrect value when attempting to remove element that exists in list");
            assertEquals(originalSize - 1,list.size, "MyArrayList.size field is not correctly modified after removing element from list");
        }
        catch (Exception e) {
            fail("Exception occurred when trying to remove element that exists in list: " + e);
        }
    }

    // Test that after an element is removed, that the remaining elements are in the correct location
    @Test
    @Timeout(value = 10)
    public void testElementsInCorrectLocationsAfterElementRemoved() {
        String[] arr = new String[] {"u", "v", "w", "x", "y", "z"};
        MyArrayList<String> list = new MyArrayList<>();
        for (String e: arr) {
            list.add(e);
        }
        try {
            list.remove("w");

            // Added during portfolio maintenance: verify the stated contract.
            assertEquals(5, list.size);
            assertArrayEquals(new Object[]{"u", "v", "x", "y", "z"}, java.util.Arrays.copyOf(list.data, list.size));

            // TODO : Verify that the remaining elements are in the correct location

        }
        catch (Exception e) {
            fail("Exception occurred when trying to remove element that exists in list: " + e);
        }
    }

    // Test that the remove method returns False when the element does not exist and that the ArrayList is not modified
    @Test
    @Timeout(value = 10)
    public void testRemoveWhenElementDoesNotExist() {
        String[] arr = new String[] {"u", "v", "w", "x", "y", "z"};
        MyArrayList<String> list = new MyArrayList<>();
        for (String e: arr) {
            list.add(e);
        }
        try {
            boolean result = list.remove("k");

            // Added during portfolio maintenance: verify the stated contract.
            assertFalse(result);
            assertEquals(arr.length, list.size);
            assertArrayEquals(arr, java.util.Arrays.copyOf(list.data, list.size));

            // TODO : Test that the return value is correct

            // TODO : Test that the ArrayList has not changed (size and element location)

        }
        catch (Exception e) {
            fail("Exception occurred when trying to remove element that does not occur in list: " + e);
        }
    }

	
	/* TODO : Write a test case to make sure your code handles removing an element where that element exists in the array more than once.
	   Consider checking the return value, the array size, and the expected position of the remaining elements. */


    /**
     *  Step 3 - Test shrinking the array when it is too large
     */

    /* Test that the array length is reduced to half its current length when the size (number of elements) of the array
	   is <= 25% of the current length
	   This test checks that removing by index decreases the array length as specified */
    @Test
    @Timeout(value = 10)
    public void testRemoveByIndexNumber() {
        String[] arr = new String[] {"u", "v", "w", "x", "y", "z"};
        MyArrayList<String> list = new MyArrayList<>();
        for (String e: arr) {
            list.add(e);
        }
        // The MyArrayList list currently has a size (number of elements) = 6 and a length = 8  
        try {
            list.remove(1); // elements = 5, length should still be 8
            assertEquals(8, list.data.length, "MyArrayList.remove(int) incorrectly shrinks array when number of elements is greater than 25% of array size");
            list.remove(1); // elements = 4, length should still be 8
            assertEquals(8, list.data.length, "MyArrayList.remove(int) incorrectly shrinks array when number of elements is greater than 25% of array size");
            list.remove(1); // elements = 3, length should still be 8
            assertEquals(8, list.data.length, "MyArrayList.remove(int) incorrectly shrinks array when number of elements is greater than 25% of array size");
            list.remove(1); // elements = 2, length should now be 4
            assertEquals(4, list.data.length, "MyArrayList.remove(int) does not shrink array when number of elements is 25% of array size");
        } catch (Exception e) {
            fail("MyArrayList.remove(int) throws exception when attempting to remove element by index: " + e);
        }
    }

    /* Test that the array length is reduced to half its current length when the size (number of elements) of the array
	   is <= 25% of the current length
	   This test checks that removing by element decreases the array length as specified */
    @Test
    @Timeout(value = 10)
    public void testRemoveByElementValue() {
        String[] arr = new String[] {"u", "v", "w", "x", "y", "z"};
        MyArrayList<String> list = new MyArrayList<>();
        for (String e: arr) {
            list.add(e);
        }
        // The MyArrayList list currently has a size (number of elements) = 6 and a length = 8  
        try {
            // Added during portfolio maintenance: verify the stated contract.
            assertTrue(list.remove("u")); assertTrue(list.remove("v"));
            assertTrue(list.remove("w")); assertTrue(list.remove("x"));
            assertEquals(2, list.size); assertEquals(4, list.data.length);
            assertEquals("y", list.get(0)); assertEquals("z", list.get(1));
			
			/* TODO : Add list.remove(E obj) calls to remove objects and check the array length.
			   Make sure you remove enough elements to trigger a reduction in the underlying array length.
			   Write an assert statement to check that removing by value decreases the array length as specified. */

        } catch (Exception e) {
            fail("MyArrayList.remove(E) throws exception when attempting to remove element by index: " + e);
        }
    }

    // TODO : Consider what happens when all of the elements are removed from the array (size = 0).  What should the underlying array length be?


    /**
     * Step 4 - Test set method
     */

    // Replace an element at a given index.  Test that both the method return value and the element at the updated index are correct.
    @Test
    @Timeout(value = 10)
    public void testReturnAndReplacementValues() {
        MyArrayList<String> list = null;
        String[] arr = {"v", "w", "x", "y", "z"};
        list = new MyArrayList<>();
        list.data = arr;
        list.size = arr.length;

        try {
            String ret = list.set(2, "dog");
            assertEquals("x", ret, "MyArrayList.set does not return correct value when attempting to set element with valid index");
            assertEquals("dog", list.get(2), "MyArrayList.set does not correctly replace value when attempting to set element with valid index");
        }
        catch (Exception e) {
            fail("MyArrayList.set throws exception when attempting to set element with valid index: " + e);
        }
    }

    // Test that the set method can handle an out of bounds index
    @Test
    @Timeout(value = 10)
    public void testSetIndexTooLarge() {
        MyArrayList<String> list = null;
        String[] arr = {"v", "w", "x", "y", "z"};
        list = new MyArrayList<>();
        list.data = arr;
        list.size = arr.length;

        try {
            list.set(400, "cat");
            // made it here but shouldn't have!
            fail("MyArrayList.set does not throw exception when called with index that is too large");
        }
        catch (IndexOutOfBoundsException e) {
            // this is expected!
        }
        catch (Exception e) {
            fail("Incorrect exception (" + e + ") thrown when calling MyArrayList.set with index that is too large");
        }
    }

    // TODO : Consider the case where a negative index is passed to the method.



    /**
     * Step 5 - Test indexOf method
     */

    // Test that the indexOf method returns correct value when element does exist in list
    @Test
    @Timeout(value = 10)
    public void testElementExists() {
        MyArrayList<String> list = null;
        String element = "x";
        String[] arr = {"v", "w", "x", "y", "z"};
        list = new MyArrayList<>();
        list.data = arr;
        list.size = arr.length;
        try {
            int index = list.indexOf(element);
            assertEquals(2, index, "MyArrayList.indexOf does not return correct value when element exists in list");
        }
        catch (Exception e) {
            fail("MyArrayList.indexOf throws exception when element exists in list: " + e);
        }
    }

    // Test that the indexOf method returns -1 when element does not exist in list
    @Test
    @Timeout(value = 10)
    public void testElementNotExists() {
        MyArrayList<String> list = null;
        String element = "a";
        String[] arr = {"v", "w", "x", "y", "z"};
        list = new MyArrayList<>();
        list.data = arr;
        list.size = arr.length;
        try {
            int index = list.indexOf(element);
            assertEquals(-1, index, "MyArrayList.indexOf does not return correct value when element does not exist " +
                    "in list");
        }
        catch (Exception e) {
            fail("MyArrayList.indexOf throws exception when element does not exist in list: " + e);
        }
    }

    /**
     * Step 6 - Test Create Constructor to Initialize Underlying Array
     */
	  
    /* Test basic functionality of MyArrayList construction
	   Check for correct elements and size */
    @Test
    @Timeout(value = 10)
    public void testConstructorBasic() {
        String[] data = new String[]{"u", "v", "w", "x", "y", "z"};

        try {
            MyArrayList<String> list = new MyArrayList<>(data);
            assertEquals("u", list.get(0), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");
            assertEquals("v", list.get(1), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");
            assertEquals("w", list.get(2), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");
            assertEquals("x", list.get(3), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");
            assertEquals("y", list.get(4), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");
            assertEquals("z", list.get(5), "MyArrayList does not correctly set elements in underlying array when input array is passed to constructor");

            assertEquals(6, list.size, "MyArrayList does not correctly set size when input array is passed to constructor");
        }
        catch (Exception e) {
            fail("MyArrayList constructor throws exception when attempting to initialize with array: " + e);
        }
    }


    // TODO : Add at least 2 more basic tests to verify MyArrayList is initialized properly and has the correct size


    // Test that the constructor can handle a null input argument
    @Test
    @Timeout(value = 10)
    public void testNullInputArgument() {
        try {
            MyArrayList<String> list = new MyArrayList<>(null);

            // Added during portfolio maintenance: verify the stated contract.
            assertEquals(0, list.size); assertEquals(4, list.data.length);
            list.add("first"); assertEquals("first", list.get(0));

            // TODO : If the argument to the constructor is a null, make sure that MyArrayList is initialized as per the homework write up.

        }
        catch (Exception e) {
            fail("MyArrayList constructor throws exception when attempting to initialize with null input array: " + e);
        }
    }

    // Test that the elements are copied from the input array
    @Test
    @Timeout(value = 10)
    public void testElementsAreCopiedToMyArrayList() {
        String[] data = new String[]{"x", "y", "z"};

        try {
            MyArrayList<String> list = new MyArrayList<>(data);

            // Added during portfolio maintenance: verify the stated contract.
            data[0] = "changed"; assertEquals("x", list.get(0));
            list.set(1, "new"); assertEquals("y", data[1]);
			
			/* TODO: When you initialize MyArrayList with an array, you should be able to edit one without changing the other.
		       Write a test case that intializes MyArrayList from an array, then modify one of them and verify the other has not changed. */

        }
        catch (Exception e) {
            fail("MyArrayList constructor throws exception when attempting to initialize with array: " + e);
        }
    }
}
