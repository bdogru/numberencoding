package com.threesixtyt.numberencoding;

import com.threesixtyt.numberencoding.service.InputReaderService;

/**
 * Hello world!
 *
 */
public class NumberEncoder 
{
    public static void main( String[] args )
    {
        InputReaderService irs = new InputReaderService();
        irs.getPiecesFromFile();
    }
}
