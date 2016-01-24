package com.threesixtyt.numberencoding.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputReaderService {

	public void getPiecesFromFile() {
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
				System.out.println(line);
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
}
