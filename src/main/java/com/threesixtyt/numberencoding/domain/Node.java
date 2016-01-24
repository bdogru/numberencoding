package com.threesixtyt.numberencoding.domain;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private char nodeValue;
	private String postfix;
	private boolean wordEnd;

	private Map<Integer, Node> next;
	
	public Node(char c ) {
		nodeValue = c;
		next = new HashMap<Integer, Node>();
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

	public Map<Integer, Node> getNext() {
		return next;
	}
}
