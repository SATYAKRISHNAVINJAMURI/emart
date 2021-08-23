/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.like.minds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.like.minds.entity.Inventory;
import com.like.minds.entity.Mart;

public class EMartApplication {
    private static Logger log = LoggerFactory.getLogger(EMartApplication.class);
	public static void main (String[] args) {
		Mart mart = new Mart(new HashSet<>(),
				new Inventory(new HashMap<>()), new HashSet<>());
		log.info("EMart Application Started");
        ArrayList<String> inputData = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
            while(in.hasNextLine())
                inputData.add(in.nextLine());
            List<String> results = processData(inputData, mart);
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            for(String res : results)
                output.println(res);
            output.close();
        } catch (IOException e) {
            log.error("IO error in input.txt or output.txt");
        }
        log.info("EMart Application Ended");
    }
    private static List<String> processData(List<String> inputs, Mart mart) {
    	List<String> output = new ArrayList<>();
    	for(String input : inputs) {
    		int start = input.indexOf("(");
    		String command = input.substring(0, start);
    		String[] arguments = input.substring(start, input.length()-1).split(",");
    		switch(command.trim()) {
    			case "createItem":
    				output.add(mart.createItem(arguments[0].trim(),
    						arguments[1].trim(), Integer.parseInt(arguments[2].trim())));
    				break;
    			case "addInventory":
    				output.add(mart.addInventory(arguments[0].trim(),
    						arguments[1].trim(), Integer.parseInt(arguments[2].trim())));
    				break;
    			case "addUser":
    				output.add(mart.addUser(arguments[0].trim(),
    						Integer.parseInt(arguments[1].trim())));
    				break;
    			case "addToCart":
    				output.add(mart.addToCart(arguments[0].trim(),
    						arguments[1].trim(), arguments[2].trim(), Integer.parseInt(arguments[3].trim())));
    				break;
    			case "updateCart":
    				output.add(mart.addToCart(arguments[0].trim(),
    						arguments[1].trim(), arguments[2].trim(), Integer.parseInt(arguments[3].trim())));
    				break;
    			case "removeFromCart":
    				output.add(mart.removeFromCart(arguments[0].trim(),
    						arguments[1].trim(), arguments[2].trim()));
    				break;
    			case "getCart":
    				output.add(mart.getCartDetails(arguments[0].trim()));
    				break;
    			case "cartCheckout":
    				output.add(mart.cartCheckout(arguments[0].trim()));
    				break;
    			default:
    				output.add("Command not found");
    		}
    	}
    	return output;
    }
}
