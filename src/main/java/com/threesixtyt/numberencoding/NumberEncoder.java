package com.threesixtyt.numberencoding;


import com.threesixtyt.numberencoding.service.DictionaryReaderService;
import com.threesixtyt.numberencoding.service.NumberEncoderService;

/**
 * Hello world!
 *
 */
public class NumberEncoder 
{
    public static void main( String[] args )
    {
        DictionaryReaderService irs = new DictionaryReaderService();
        irs.getDictionaryFromFile();
        NumberEncoderService nes = new NumberEncoderService();
        nes.processInputFile();
//        nes.getLineAndPrintVariations("4-753168781103080");
    }
}
