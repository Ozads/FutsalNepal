package com.ozads.futsalnepal.util;

import java.math.BigInteger;
import java.security.SecureRandom;


public class RandomPassword {
	
	private static SecureRandom random = new SecureRandom();

	public static String newPassword() {
		return new BigInteger(50, random).toString(32);
	}

}
