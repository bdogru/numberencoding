package com.threesixtyt.numberencoding.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.threesixtyt.numberencoding.domain.Node;

/**
 * Contains the Dictionary tree
 * 
 * @author Bekir Dogru
 *
 */
public class Root {
	/**
	 * dictionary tree
	 */
	public static Map<Integer, List<Node>> dictionary = new HashMap<Integer, List<Node>>();
}
