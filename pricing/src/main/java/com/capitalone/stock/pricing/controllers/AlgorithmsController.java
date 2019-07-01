package com.capitalone.stock.pricing.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmsController {

    @RequestMapping(method = {
        RequestMethod.GET }, value = "algorithms/longestSubString", produces = MediaType.APPLICATION_JSON_VALUE)
public @ResponseBody ResponseEntity<Integer> longestSubString(
        @RequestParam(value = "value", required = true) String value)
    {
        int max = 0;
        int counter = 0;
        char[] letters = value.toCharArray();
        Set<Character> found = new HashSet<>();
        int priorStart = 0;
        for (int i = 0; i < letters.length; i++)
        {
            if(found.contains(letters[i]))
            {
                if(counter > max)
                    max = counter;

                found.clear();
                counter = 0;
                
                i = priorStart;
                priorStart = i + 1;
            }
            else {
                counter++;
                found.add(letters[i]);
            }
        }
        
        max = max > counter ? max : counter;

        return new ResponseEntity<>(max, HttpStatus.OK);
    }

    //http://localhost:5000/algorithms/two-sum?nums=2&nums=7&nums=11&nums=15&target=9
    @RequestMapping(method = {
            RequestMethod.GET }, value = "algorithms/two-sum", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Integer[]> twoSum(
            @RequestParam(value = "nums", required = true) int[] nums,
            @RequestParam(value = "target", required = true) int target)
	{
        if(nums==null || nums.length < 2)
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);

        Integer[] results = new Integer[2];
        HashMap<Integer, Integer> found = new HashMap<>();
        found.put(nums[0],0);
        for(int i = 1; i < nums.length; i++)
        {
            int diff = target - nums[i];
            if(found.containsKey(diff))
            {
                results[0] = found.get(diff);
                results[1] = i;
                break; 
            }
            else
            {
                found.put(nums[i],i);
            }
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

	private String reverseString(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++)
        {
            stack.push(s.charAt(i));
        }
        char[] reverse = new char[s.length()];
        int i = 0;
        while(!stack.isEmpty())
        {
            reverse[i] = stack.pop();
            i++;
        }
        return String.valueOf(reverse);
    }


}

class MinStack {
    
    PriorityQueue<Integer> pq;
    Stack<Integer> stack;
    
    /** initialize your data structure here. */
    public MinStack() {
        //Example of lambda in priority queue for comparator.
        pq = new PriorityQueue<>((a,b)->(a-b));
        stack = new Stack<>();
    }
    
    public void push(int x) {
        Integer val = new Integer(x);
        stack.push(val);
        pq.add(val);
    }
    
    public void pop() {
        pq.remove(stack.pop());
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return pq.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */