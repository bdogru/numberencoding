package com.threesixtyt.numberencoding.config;

import java.util.HashMap;
import java.util.Map;

public class Encoder {
	private static Map<Character, Integer> encodingMap = null;
	public static Integer get(Character c) {
		if(encodingMap == null) {
			initializeMap();
		}
		return encodingMap.get(c);
	}
	private static void initializeMap() {
		encodingMap = new HashMap<Character, Integer>();

		encodingMap.put('E', 0);
		encodingMap.put('e', 0);
		
		encodingMap.put('J', 1);
		encodingMap.put('N', 1);
		encodingMap.put('Q', 1);
		encodingMap.put('j', 1);
		encodingMap.put('n', 1);
		encodingMap.put('q', 1);
		
		encodingMap.put('R', 2);
		encodingMap.put('W', 2);
		encodingMap.put('X', 2);
		encodingMap.put('r', 2);
		encodingMap.put('w', 2);
		encodingMap.put('x', 2);
		
		encodingMap.put('D', 3);
		encodingMap.put('S', 3);
		encodingMap.put('Y', 3);
		encodingMap.put('d', 3);
		encodingMap.put('s', 3);
		encodingMap.put('y', 3);
		
		encodingMap.put('F', 4);
		encodingMap.put('T', 4);
		encodingMap.put('f', 4);
		encodingMap.put('t', 4);
		
		encodingMap.put('A', 5);
		encodingMap.put('M', 5);
		encodingMap.put('a', 5);
		encodingMap.put('m', 5);
		
		encodingMap.put('C', 6);
		encodingMap.put('I', 6);
		encodingMap.put('V', 6);
		encodingMap.put('c', 6);
		encodingMap.put('i', 6);
		encodingMap.put('v', 6);
		
		encodingMap.put('B', 7);
		encodingMap.put('K', 7);
		encodingMap.put('U', 7);
		encodingMap.put('b', 7);
		encodingMap.put('k', 7);
		encodingMap.put('u', 7);
		
		encodingMap.put('L', 8);
		encodingMap.put('O', 8);
		encodingMap.put('P', 8);
		encodingMap.put('l', 8);
		encodingMap.put('o', 8);
		encodingMap.put('p', 8);
		
		encodingMap.put('G', 9);
		encodingMap.put('H', 9);
		encodingMap.put('Z', 9);
		encodingMap.put('g', 9);
		encodingMap.put('h', 9);
		encodingMap.put('z', 9);
		
	}
	
}
