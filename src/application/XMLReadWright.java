package application;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLReadWright {
    
    
    
    public void logAnswer(String userID,String answer, String percenDiff,String isRight , String xmlFilePath) {

    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder=null;
    	Document doc;
    	Element root;
    	Element qestion;
    	
    	
       
	try {
		File logFile = new File(xmlFilePath);
		builder = factory.newDocumentBuilder();
		
			if(logFile.exists()) {
				
				doc = builder.parse(xmlFilePath);
				root=doc.getDocumentElement();
				
			}else {
				
				doc = builder.newDocument();
				
				root      = doc.createElement("root");
				
				
			}
			
			
			
			qestion   = doc.createElement("qestion");
			

			
			
			root.appendChild(qestion);
			
			qestion.setAttribute("qestionID", "1");
			qestion.setAttribute("percenDiff", percenDiff);
			qestion.setAttribute("userID", userID);
			qestion.setAttribute("answer", answer);
			qestion.setAttribute("isRight", isRight);
			
			
			
			
			
			

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
            
		
 
            System.out.println("Done creating XML File");
            
            
            
	}  catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

        
	
	
	
	
    }

    
}
