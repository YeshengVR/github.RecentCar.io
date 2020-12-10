package com.rentCar.Util;

public class DataUtil {
	
	public static boolean login(String truepassword,String password){
			if(password.equals(truepassword)){
				return true;
			}
		return false;
	}
}
