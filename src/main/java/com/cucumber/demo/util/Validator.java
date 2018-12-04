package com.cucumber.demo.util;

public class Validator {
	public static boolean threasholdCheck(Double dollar) {
		if(Math.floor(dollar / 100000000) > 0)
			return true;
		else
			return false;
	}
}
