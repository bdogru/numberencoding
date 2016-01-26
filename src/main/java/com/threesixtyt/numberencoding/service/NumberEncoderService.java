package com.threesixtyt.numberencoding.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.threesixtyt.numberencoding.domain.EncodeVariation;
import com.threesixtyt.numberencoding.domain.Node;
import com.threesixtyt.numberencoding.storage.Root;

public class NumberEncoderService {
	
	public void processInputFile() {

		FileReader fr = null;
		try {
			fr = new FileReader("input.txt");
		} catch (FileNotFoundException fnf) {
			throw new IllegalArgumentException("Input file couldn't be found.", fnf);
		}
		BufferedReader br = new BufferedReader(fr);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				getLineAndPrintVariations(line);
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
	
	public void getLineAndPrintVariations(String line) {
		EncodeVariation variations = generateResults(line);
		for(String encode : variations.getEncodedList()) {
			System.out.print(variations.getPhoneNumber());
			System.out.print(": ");
			System.out.print(encode);
			System.out.print(System.lineSeparator());
		}
	}

	private EncodeVariation generateResults(String phoneNumber) {
		EncodeVariation variations = new EncodeVariation(phoneNumber);
		List<String> encodes = processor(getOnlyNumerics(phoneNumber), 0, Root.dictionary, new StringBuilder(), true, true);
		variations.setEncodedList(encodes);
		return variations;
	}

	private String getOnlyNumerics(String str) {

		if (str == null) {
			return null;
		}

		StringBuilder strBuff = new StringBuilder();
		char c;

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);

			if (Character.isDigit(c)) {
				strBuff.append(c);
			}
		}
		return strBuff.toString();
	}

	private List<String> processor(String phoneNumber, int index, Map<Integer, List<Node>> dictionary,
			StringBuilder prefix, boolean isRoot, boolean isDigitAllowed) {
		List<String> results = new ArrayList<String>();
		boolean isLastDigitOfThePhoneNumber = false;
		if(index>= phoneNumber.length()) {
			return results;
		}
		if (index == phoneNumber.length() - 1) {
			isLastDigitOfThePhoneNumber = true;
		}
		Integer key = Integer.parseInt("" + phoneNumber.charAt(index));
		if (dictionary.containsKey(key)) {
			for (Node nd : dictionary.get(key)) {
				if (isLastDigitOfThePhoneNumber) {
					if (nd.isWordEnd()) {
						StringBuilder sb = new StringBuilder(prefix);
						sb.append(nd.getNodeValue());
						sb.append(nd.getPostfix());
						results.add(sb.toString());
					}
				} else {
					if (nd.isWordEnd()) {
						StringBuilder sb = new StringBuilder(prefix);
						sb.append(nd.getNodeValue());
						sb.append(nd.getPostfix());
						sb.append(" ");
						results.addAll(processor(phoneNumber, index + 1, Root.dictionary, sb, true, true));
					}
					StringBuilder sb = new StringBuilder(prefix);
					sb.append(nd.getNodeValue());
					sb.append(nd.getPostfix());
					results.addAll(processor(phoneNumber, index + 1, nd.getNext(), sb, false, true));
				}
			}
		}
		if (results.isEmpty() && isRoot && isDigitAllowed) {
			if (isLastDigitOfThePhoneNumber) {
				StringBuilder sb = new StringBuilder(prefix);
				sb.append(key);
				results.add(sb.toString());
			} else {
				StringBuilder sb = new StringBuilder(prefix);
				sb.append(key);
				sb.append(" ");
				results.addAll(processor(phoneNumber, index + 1, Root.dictionary, sb, true, false));
			}
		}
		return results;
	}

}
