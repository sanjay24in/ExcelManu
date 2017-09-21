package org.test;

/**
 * Application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	/*final String XML_FILE_NAME = "C:\\Users\\Abhishek\\Desktop\\JobSearch\\Aviva\\SampleXML.xml";
    	final String Excel_FILE_NAME = "C:\\Users\\Abhishek\\Desktop\\JobSearch\\Aviva\\TestCase.xlsx";
    	*/
    	
        System.out.println( "Excel Read & Write" );
        
        ExcelReader reader = new ExcelReader();
        reader.readData();
        
        System.out.println("Adding data in xml");
        Xmlreader readWriteXml = new Xmlreader();
        readWriteXml.appendXMLTg("Start-Date","2017-09-21","staff");
    }	
}
