
package jtm.extra05;

//TODO #1
//Import necessary classes from javax.xml.* and, if necessary org.w3c.dom.*

import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;


public class XMLCars {

	/*- TODO #2
	 * Declare static variables to remember previously generated structure of XML
	 */

//	static String schemaString = "http://www.w3.org/2001/XMLSchema";

	static DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

	static DocumentBuilder documentBuilder;

	static {

		try {

			 documentBuilder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	static Document document = documentBuilder.newDocument();

	// TODO #3
	/*- Implement method which adds new car elements into XML structure.
	 * Note, that:
	 *   1. if method is called 1st time, one root element "car" should be
	 *      created, but if method is called again, just new "car" element is added into
	 *      "cars" tree.
	 *   2. Car id should be padded with leading zeroes if id is smaller than 1111.
	 *      E.g. if int id=33, then  attribute of XML should be id="0033".
	 *   3. At the end of car element XML comment should be added with value of passed notes
	 *      (This is not checked by validator using XSD schema,
	 *      but is checked when generated XML is produced as string.)
	 *      
	 * Hint:
	 *   Look at https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html and
	 *           https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/package-summary.html
	 */
	public void addCar(int id, String model, String color, int year, float price, String notes) throws DOMException {
	


			Element root;

			if (document == null) {	
				root = document.createElement("cars");
				document.appendChild(root);
			} else { 
				root = document.getDocumentElement();
			
			}

				Element carElem = document.createElement("car");
				root.appendChild(carElem);


				Attr idAttr = document.createAttribute("id");
				String id2 = String.format("%04d", id);
				idAttr.setValue(id2);
				carElem.setAttributeNode(idAttr);
				
				Attr notesAttr = document.createAttribute("notes");
				notesAttr.setValue(notes);
				carElem.setAttributeNode(notesAttr);
			
				Element modelElem = document.createElement("model");
				modelElem.setNodeValue(model);
				carElem.appendChild(modelElem);
				
	
				Element colorElem = document.createElement("color");
				colorElem.setNodeValue(color);
				carElem.appendChild(colorElem);
	
				Element yearElem = document.createElement("year");
				String year2 = Integer.toString(year);
				yearElem.setNodeValue(year2);
				carElem.appendChild(yearElem);
	
				Element priceElem = document.createElement("price");
				String price2 = Float.toString(price);
				priceElem.setNodeValue(price2);
				carElem.appendChild(priceElem);
			
				Comment com = document.createComment("notes");
				com.setTextContent(notes);
		

	}

	public String getXML() throws Exception {
		/*- TODO No. 4: Write a code that will create String containing XML as that matches car.xsd requirements.
		 * 
		 * HINT look at:
		 * https://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/DocumentBuilder.html
		 * 
		 * Note, that XML should be "prettied" with line breaks and 
		 * indentations of 2 spaces for internal elements
		 * 
		 * HINT: look at:
		 * https://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Transformer.html
		 */

		// PRETTY PRINT
		Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(document), new StreamResult(out));
        System.out.println(out.toString());   

		return out.toString();
	}	

	/*-
	 * @param schemaSource — String containing XSD schema definition from car.xsd file
	 * @param xmlSource — String containing XML for car
	 * @return — true, if xmlSource is valid
	 * @throws Exception — if xmlSource is invalid
	 *         (will be thrown by javax.xml.validation.Validator automatically)
	 */
	public static boolean validateXMLSchema(String schemaSource, String xmlSource) throws Exception {
		/*- TODO No. 2: Write a code to validate prepared XML source according to schema source
		 * Note that Exception should be thrown, if passed XML file is invalid.
		 * HINT:
		 * Use https://docs.oracle.com/javase/7/docs/api/javax/xml/validation/Validator.html
		 */
		return false;
	}

} // end class
