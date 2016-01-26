package com.threesixtyt.numberencoding;


import com.threesixtyt.numberencoding.service.DictionaryReaderService;

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
    }
}
