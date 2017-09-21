/**
 * 
 */
package org.test;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 * @author Abhishek
 *
 */
public class Xmlreader {
	
	private static final String FILE_NAME = "C:\\Users\\Abhishek\\Desktop\\JobSearch\\Aviva\\SampleXML.xml";

	public boolean appendXMLTg(String tagNamStr,String tagVal,String nodeName){
		try{

			String filepath = FILE_NAME;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			//Node name below which to add
			Node nodeNameNd = doc.getElementsByTagName(nodeName).item(0);

			Element tagNamElem = doc.createElement(tagNamStr);
			tagNamElem.appendChild(doc.createTextNode(tagVal));
			nodeNameNd.appendChild(tagNamElem);
			
			
			System.out.println("Updated xml is below: \n"+doc.toString());

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		}  catch (IOException ioe) {			
			ioe.printStackTrace();
			return false;
		} catch (SAXException sae) {			
			sae.printStackTrace();
			return false;
		} catch (Exception exe) {
			exe.printStackTrace();
			return false;
		}
		return true;
	}
	
}
