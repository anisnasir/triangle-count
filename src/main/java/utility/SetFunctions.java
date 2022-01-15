package utility;

import java.util.HashSet;
import java.util.Set;

public class SetFunctions {
	public static HashSet<String> intersectionSet (Set<String> set1, Set<String> set2) {
		if(set1 == null)
			return new HashSet<String>();
		else if (set2 == null)
			return new HashSet<String>();
		Set<String> a;
		Set<String> b;
		HashSet<String> returnSet = new HashSet<String>();
		if (set1.size() <= set2.size()) {
			a = set1;
			b = set2; 
		} else {
			a = set2;
			b = set1;
		}
		for (String e : a) {
			if (b.contains(e)) {
				returnSet.add(e);
			} 
		}
		return returnSet;
	}
	
	public static int intersection (Set<Long> set1, Set<Long> set2) {
		Set<Long> a;
		Set<Long> b;
		int counter = 0;
		if(set1 == null || set2 == null)
			return 0;
		if (set1.size() <= set2.size()) {
			a = set1;
			b = set2; 
		} else {
			a = set2;
			b = set1;
		}
		for (Long e : a) {
			if (b.contains(e)) {
				counter++;
			} 
		}
		return counter;
	}
	
}
