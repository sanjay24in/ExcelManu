package org.test;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        ExcelReader reader = new ExcelReader();
        reader.readData();
    }
}