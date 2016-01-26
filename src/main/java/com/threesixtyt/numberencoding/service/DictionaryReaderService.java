package com.threesixtyt.numberencoding.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.threesixtyt.numberencoding.config.Encoder;
import com.threesixtyt.numberencoding.domain.Node;
import com.threesixtyt.numberencoding.storage.Root;

public class DictionaryReaderService {

	public void getDictionaryFromFile() {
		FileReader fr = null;
		try {
			fr = new FileReader("dictionary.txt");
		} catch (FileNotFoundException fnf) {
			throw new IllegalArgumentException("Input file couldn't be found.", fnf);
		}
		BufferedReader br = new BufferedReader(fr);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				processLine(line, Root.dictionary, 0);
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Input file couldn't be read.", e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				throw new IllegalArgumentException("Buffered reader couldn't be closed.", e);
			}
		}
	}

	private void processLine(String line, Map<Integer, List<Node>> dictionary, int i) {
		if(line.isEmpty()) {
			return;
		}
		Integer key = Encoder.get(line.charAt(i));
		char nodeValue = line.charAt(i);
		StringBuffer postfix = new StringBuffer();
		boolean isWordEnd = false;
		i++;
		while (i < line.length() && Encoder.get(line.charAt(i)) == null) {
			postfix.append(line.charAt(i));
			i++;
		}
		if (i == line.length()) {
			isWordEnd = true;
		}
		String postfixStr = postfix.toString();
		if (!dictionary.containsKey(key)) {
			dictionary.put(key, new ArrayList<Node>());
		}
		Node next = null;
		for (Node nd : dictionary.get(key)) {
			if (nd.getNodeValue() == nodeValue && nd.getPostfix().equals(postfixStr)) {
				next = nd;
				break;
			}
		}
		if (next == null) {
			next = new Node(nodeValue);
			next.setPostfix(postfixStr);
			dictionary.get(key).add(next);
		}
		if (isWordEnd) {
			next.setWordEnd(isWordEnd);
			return;
		}

		processLine(line, next.getNext(), i);

	}
}
