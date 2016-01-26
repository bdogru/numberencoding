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

/**
 * Main processing service class
 * 
 * @author Bekir Dogru
 *
 */
public class NumberEncoderService {

	/**
	 * Reads input file line by line and calls processing method
	 */
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

	/**
	 * Takes the line, get encoding variations by calling the and recursive
	 * method then prints results.
	 * 
	 * @param line
	 *            a line from the input file
	 */
	public void getLineAndPrintVariations(String line) {
		EncodeVariation variations = new EncodeVariation(line);
		List<String> encodes = processor(getOnlyNumerics(line), 0, Root.dictionary, new StringBuilder(), true);
		variations.setEncodedList(encodes);
		for (String encode : variations.getEncodedList()) {
			System.out.print(variations.getPhoneNumber());
			System.out.print(": ");
			System.out.print(encode);
			System.out.print(System.lineSeparator());
		}
	}

	/**
	 * Trims the non-digit characters from the phone number
	 * 
	 * @param str
	 *            phone number from the input file
	 * @return phone number that non-digit characters are removed from
	 */
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

	/**
	 * Recursive method that encodes the phone number
	 * 
	 * @param phoneNumber
	 *            phone number to encode
	 * @param index
	 *            indicates the index of the last non-encoded digit
	 * @param dictionary
	 *            subtree or root of the dictionary tree
	 * @param prefix
	 *            encoded word till now
	 * @param isDigitAllowed
	 *            flag that indicates if a digit can be put if no encoded
	 *            variation is found
	 * @return
	 */
	private List<String> processor(String phoneNumber, int index, Map<Integer, List<Node>> dictionary,
			StringBuilder prefix, boolean isDigitAllowed) {
		List<String> results = new ArrayList<String>();
		boolean isLastDigitOfThePhoneNumber = false;
		if (index >= phoneNumber.length()) {
			return results;
		}
		if (index == phoneNumber.length() - 1) {
			isLastDigitOfThePhoneNumber = true;
		}
		Integer key = Integer.parseInt("" + phoneNumber.charAt(index));

		/**
		 * If dictionary don't has the digit don't look at the tree to prevent
		 * NullPointer Exception
		 */
		if (dictionary.containsKey(key)) {
			/**
			 * Loops on the encoded letters of the specified digit
			 */
			for (Node nd : dictionary.get(key)) {
				/*
				 * If this is the last digit of the phone number node should be
				 * end of a word
				 */
				if (isLastDigitOfThePhoneNumber) {
					if (nd.isWordEnd()) {
						StringBuilder sb = new StringBuilder(prefix);
						sb.append(nd.getNodeValue());
						sb.append(nd.getPostfix());
						results.add(sb.toString());
					}
				} else {
					/**
					 * If this node is end of a word adds word to the result and
					 * calls processor method with the root of the dictionary
					 * tree to find following encode word, adds the returned
					 * list to results
					 */
					if (nd.isWordEnd()) {
						StringBuilder sb = new StringBuilder(prefix);
						sb.append(nd.getNodeValue());
						sb.append(nd.getPostfix());
						sb.append(" ");
						results.addAll(processor(phoneNumber, index + 1, Root.dictionary, sb, true));
					}
					/**
					 * Adds node to result string and calls processor method
					 * with subtree of the current node, then adds the returned
					 * list to results
					 */
					StringBuilder sb = new StringBuilder(prefix);
					sb.append(nd.getNodeValue());
					sb.append(nd.getPostfix());
					results.addAll(processor(phoneNumber, index + 1, nd.getNext(), sb, false));
				}
			}
		}

		/**
		 * If result list is empty and digit is allowed instead of encoding puts
		 * non-encoded digit to result string and calls processor method to
		 * encode remaining of the phone number, then adds returned list to
		 * result list
		 */
		if (results.isEmpty() && isDigitAllowed) {
			if (isLastDigitOfThePhoneNumber) {
				StringBuilder sb = new StringBuilder(prefix);
				sb.append(key);
				results.add(sb.toString());
			} else {
				StringBuilder sb = new StringBuilder(prefix);
				sb.append(key);
				sb.append(" ");
				results.addAll(processor(phoneNumber, index + 1, Root.dictionary, sb, false));
			}
		}
		return results;
	}

}
