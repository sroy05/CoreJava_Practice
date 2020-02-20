package com.stream.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class HashMapIterator {
	
	public String getmaxCount(List<String> sweetlist) {
		HashMap<String,Integer> h=new HashMap<String,Integer>();
		h.put("Laddu",1);
		h.put("gulabJamun", 2);
		h.put("Jalebi",3);
		h.put("4", 1);
		 Entry<K, V> maxEntry = Collections.max(h.entrySet(), (Entry<K, V> e1, Entry<K, V> e2) -> 
		 e1.getValue().compareTo(e2.getValue()));
		return ""
;		
	}

}
