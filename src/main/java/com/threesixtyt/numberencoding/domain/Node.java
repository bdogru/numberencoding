package com.threesixtyt.numberencoding.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * This class is used to store dictionary in a tree format.
 * 
 * @author Bekir Dogru
 *
 */
public class Node {

	/**
	 * a decodable character from words in the dictionary
	 */
	private char nodeValue;

	/**
	 * undecodable strings from the words
	 */
	private String postfix;

	/**
	 * a flag that indicates if this node is an end to a word.
	 */
	private boolean wordEnd;

	/**
	 * Holds the subtree
	 */
	private Map<Integer, List<Node>> next;

	public Node(char c) {
		nodeValue = c;
		next = new HashMap<Integer, List<Node>>();
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public boolean isWordEnd() {
		return wordEnd;
	}

	public void setWordEnd(boolean wordEnd) {
		this.wordEnd = wordEnd;
	}

	public char getNodeValue() {
		return nodeValue;
	}

	public Map<Integer, List<Node>> getNext() {
		return next;
	}
}
