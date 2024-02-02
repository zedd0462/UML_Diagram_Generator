package org.mql.java.umlgen.utils;

import java.util.Collection;
import java.util.Collections;

public class MathUtils {

	private MathUtils() {}
	
	public static int sum(Collection<Integer> list) {
	     int sum = 0; 
	     for (int i : list)
	         sum = sum + i;
	     return sum;
	}
	
	public static int max(Collection<? extends Integer> coll) {
		return Collections.max(coll);
	}
	
	

}
