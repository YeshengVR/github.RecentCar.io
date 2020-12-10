package com.rentCar.Common.Methods;

import java.util.Scanner;

public class Input {

	public static Scanner input = new Scanner(System.in);
	public static String nextLine(){
		String s = input.nextLine().trim();
		return s;
	}
}
