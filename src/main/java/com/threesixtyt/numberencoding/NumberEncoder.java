package com.threesixtyt.numberencoding;

import com.threesixtyt.numberencoding.service.DictionaryReaderService;
import com.threesixtyt.numberencoding.service.NumberEncoderService;

/**
 * Main class of the project
 * 
 * @author Bekir Dogru
 *
 */
public class NumberEncoder {
	public static void main(String[] args) {
		DictionaryReaderService irs = new DictionaryReaderService();
		irs.getDictionaryFromFile();
		NumberEncoderService nes = new NumberEncoderService();
		nes.processInputFile();
	}
}
