/*
 * This file will hold all of your testcases. Remember, to run all
 * of your tests, right-click on 'RunTests.java' and select 'Run As' ->
 * JUnit Test.
 */
import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class RecursionTestClass {
	
	/*
	 * Here I have provided an example of one of the tests that I
	 * would write. For each of your tests, leave a brief comment
	 * above the test specifying its purpose. For example, for this
	 * test, I might write, "indexOf_test1 tests when s2 is not a 
	 * substring of s1. This should return -1."
	 */
	// s1 is "Hello", s2 is "World", should return -1
    @Test
    public void test_indexOf_test1() {
        int result = Recursion.indexOf("Hello", "World");
        System.out.println("indexOf(Hello, World), got " + result);
        assertEquals(-1, result);
    }
    // s1 is "hamburger", s2 is "burg", should return 3
    @Test
    public void test_indexOf_test2() {
        int result = Recursion.indexOf("hamburger", "burg");
        System.out.println("indexOf(hamburger, burg), got " + result);
        assertEquals(3, result);
    }
    // empty string in s1, s2 is "cheese", should return -1
    @Test
    public void test_indexOf_test3() {
        int result = Recursion.indexOf("", "cheese");
        System.out.println("indexOf( , cheese), got " + result);
        assertEquals(-1, result);
    }
    // s1 is hahahaha, s2 is empty string, should return -1
    @Test
    public void test_indexOf_test4() {
        int result = Recursion.indexOf("hahahaha", "");
        System.out.println("indexOf(hahahaha, ), got " + result);
        assertEquals(-1, result);
    }
    
    // 6 numbers in stack, k is 3, should return 3
    @Test
    public void test_removeEvenNumbers_test1() {
    	Stack<Integer> test_stack1 = new Stack<Integer>();
    	test_stack1.push(2);
    	test_stack1.push(4);
    	test_stack1.push(6);
    	test_stack1.push(7);
    	test_stack1.push(3);
    	test_stack1.push(8);
    	int res = Recursion.removeEvenNumbers(test_stack1, 3);
    	System.out.println("removeEvenNumbers(2,4,6,7,3,8), 3), got " + res);
    	assertEquals(3, res);
    }
    // all even numbers in stack, 15 is k, should return 6
    @Test
    public void test_removeEvenNumbers_test2() {
    	Stack<Integer> test_stack1 = new Stack<Integer>();
    	test_stack1.push(2);
    	test_stack1.push(4);
    	test_stack1.push(6);
    	test_stack1.push(8);
    	test_stack1.push(88);
    	test_stack1.push(64);
    	int res = Recursion.removeEvenNumbers(test_stack1, 15);
    	System.out.println("removeEvenNumbers(2,4,6,8,88,64), 15), got " + res);
    	assertEquals(6, res);
    }
    // all odd numbers in stack, k is 4, should return 0
    @Test
    public void test_removeEvenNumbers_test3() {
    	Stack<Integer> test_stack1 = new Stack<Integer>();
    	test_stack1.push(3);
    	test_stack1.push(57);
    	test_stack1.push(9);
    	test_stack1.push(33);
    	test_stack1.push(75);
    	int res = Recursion.removeEvenNumbers(test_stack1, 4);
    	System.out.println("removeEvenNumbers(3,57,9,33,75), 5), got " + res);
    	assertEquals(0, res);
    }
    // empty stack, k is 3 should return 0
    @Test
    public void test_removeEvenNumbers_test4() {
    	Stack<Integer> test_stack1 = new Stack<Integer>();
    	int res = Recursion.removeEvenNumbers(test_stack1, 3);
    	System.out.println("removeEvenNumbers(0), 3), got " + res);
    	assertEquals(0, res);
    }
    
    @Test
    // 0 number test, should return 0
    public void test_evenDigits_test1() {
    	int res = Recursion.evenDigits(0);
    	System.out.println("evenDigits(0), got " + res);
    	assertEquals(0, res);
    }
    @Test
    // 3 digit number test
    public void test_evenDigits_test2() {
    	int res = Recursion.evenDigits(425);
    	System.out.println("evenDigits(425), got " + res);
    	assertEquals(42, res);
    }
    // all even numbers
    @Test
    public void test_evenDigits_test3() {
    	int res = Recursion.evenDigits(44226688);
    	System.out.println("evenDigits(44226688), got " + res);
    	assertEquals(44226688, res);
    }
    // all odd numbers
    @Test
    public void test_evenDigits_test4() {
    	int res = Recursion.evenDigits(335793);
    	System.out.println("evenDigits(335793), got " + res);
    	assertEquals(0, res);
    }
    // expression is "(((1+2)*(3+1))+(1*(2+2)))", should return 16
    @Test
    public void test_evaluate_test1() {
    	String cal = "(((1+2)*(3+1))+(1*(2+2)))";
    	Queue<Character> test_q = new LinkedList<Character>();
        for (int i = 0; i < cal.length(); i++) {
        	test_q.add(cal.charAt(i));
        }
        int res = Recursion.evaluate(test_q);
    	System.out.println("evaluate( (((1+2)*(3+1))+(1*(2+2))) ), got " + res);
    	assertEquals(16, res);
    }
    // expression is "", should return 0
    @Test
    public void test_evaluate_test2() {
    	String cal = "";
    	Queue<Character> test_q = new LinkedList<Character>();
        for (int i = 0; i < cal.length(); i++) {
        	test_q.add(cal.charAt(i));
        }
        int res = Recursion.evaluate(test_q);
    	System.out.println("evaluate( ), got " + res);
    	assertEquals(0, res);
    }
    // expression is "(2*5)", should return 10
    @Test
    public void test_evaluate_test3() {
    	String cal = "";
    	Queue<Character> test_q = new LinkedList<Character>();
        for (int i = 0; i < cal.length(); i++) {
        	test_q.add(cal.charAt(i));
        }
        int res = Recursion.evaluate(test_q);
    	System.out.println("evaluate( (2*5) ), got " + res);
    	assertEquals(10, res);
    }
    // 1,2,3 in stack
    @Test
    public void test_repeatStack_test1() {
    	Stack<Integer> test_stack = new Stack<Integer>();
    	test_stack.push(1);
    	test_stack.push(2);
    	test_stack.push(3);
    	Recursion.repeatStack(test_stack);
    	System.out.println("repeatStack(1,2,3), got" +test_stack);
    }
    // 0 in stack
    @Test
    public void test_repeatStack_test2() {
    	Stack<Integer> test_stack = new Stack<Integer>();
    	test_stack.push(0);
    	Recursion.repeatStack(test_stack);
    	System.out.println("repeatStack(0), got" +test_stack);
    }
    // empty stack test
    @Test
    public void test_repeatStack_test3() {
    	Stack<Integer> test_stack = new Stack<Integer>();
    	Recursion.repeatStack(test_stack);
    	System.out.println("repeatStack(), got" +test_stack);
    }
    // 1,2,3 in queue test
    @Test
    public void test_doubleElements_test1() {
    	Queue<Integer> test_q = new LinkedList<Integer>();
    	test_q.add(1);
    	test_q.add(2);
    	test_q.add(3);
    	System.out.print("doubleElements(1,2,3), got ");
    	Recursion.doubleElements(test_q);
    }
    // empty stack test
    @Test
    public void test_doubleElements_test2() {
    	Queue<Integer> test_q = new LinkedList<Integer>();
    	System.out.print("doubleElements(), got ");
    	Recursion.doubleElements(test_q);
    }
    // negative numbers
    @Test
    public void test_doubleElements_test3() {
    	Queue<Integer> test_q = new LinkedList<Integer>();
    	test_q.add(-3);
    	test_q.add(4);
    	test_q.add(-5);
    	System.out.print("doubleElements(-3, 4,-5), got ");
    	Recursion.doubleElements(test_q);
    }
    
}
