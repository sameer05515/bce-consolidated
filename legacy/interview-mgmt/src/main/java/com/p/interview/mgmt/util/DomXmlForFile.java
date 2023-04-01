package com.p.interview.mgmt.util;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class DomXmlForFile {

	private static DomXmlForFile thisInstance;
	private static Byte[] justForLock = new Byte[1];

	/**
	 * Our goal is to create a DOM XML tree and then print the XML.
	 */
	public static void main(String args[]) {
		//new DomXmlForFile().printAllFolders("d:/ebooks", "");
	}

	private DomXmlForFile() {

	}

	public static DomXmlForFile getInstance() {

		if (thisInstance == null) {
			synchronized (justForLock) {
				if (thisInstance == null) {
					thisInstance = new DomXmlForFile();
				}
			}
		}
		return thisInstance;
	}

//	public String printAllFolders(String filePath, String fileddPath) {
//		try {
//			/**
//			 * ################################
//			 *
//			 * Creating an empty XML Document
//			 *
//			 * ################################
//			 */
//			/** We need a Document */
//			createDocument();
//			/**
//			 * ################################
//			 *
//			 * Creating the XML tree
//			 *
//			 * ################################
//			 */
//			/** create the root element and add it to the document */
//			Element root = dom.createElement("root");
//			dom.appendChild(root);
//			if (filePath != null && !filePath.trim().equalsIgnoreCase("")) {
//				File file = new File(filePath);
//				Element bookEle = createFileElement(file);
//				if (bookEle != null) {
//					root.appendChild(bookEle);
//				}
//			}
//			// System.out.println(dom.toString());
//			// printToFile();
//			System.out.println(output());
//			return output();
//
//			// Element rooty = dom.getDocumentElement();
//			// String s = rooty.toString();
//			// System.out.println(s);
//
//			// System.out.println(getStringFromDocument(dom));
//		} catch (DOMException e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}

	/**
	 * Helper method which creates a XML element <Book>
	 * 
	 * @param b
	 *            The book for which we need to create an xml representation
	 * @return XML element snippet representing a book
	 */
	private Element createFileElement(File b) {

		Element bookEle = null;
		if (b.exists()) {
			bookEle = dom.createElement("File");
			bookEle.setAttribute("name", b.getAbsolutePath());
			bookEle.setAttribute("simpleName", b.getName());
			if (b.isFile()) {
				bookEle.setAttribute("type", "file");
			} else if (b.isDirectory()) {
				bookEle.setAttribute("type", "directory");
				File[] bKeChilddren = b.listFiles();
				if (bKeChilddren != null && bKeChilddren.length > 0) {
					for (File bKeCh : bKeChilddren) {
						Element authEle = createFileElement(bKeCh);
						if (authEle != null) {
							bookEle.appendChild(authEle);
						}
					}
				}
			}
		}
		return bookEle;
	}

	/**
	 * This method uses Xerces specific classes prints the XML document to file.
	 */
	private void printToFile() {

//		try {
			/** print */
//			OutputFormat format = new OutputFormat(dom);
//			format.setIndenting(true);

			/**
			 * to generate output to console use this serializer XMLSerializer
			 * serializer = new XMLSerializer(System.out, format);
			 */

			/**
			 * to generate a file output use fileoutputstream instead of
			 * system.out
			 */
//			XMLSerializer serializer = new XMLSerializer(new FileOutputStream(
//					new File(new File("").getAbsolutePath()
//							+ "/xml-src/com/ist/xml/example/book.xml")), format);
			// ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
			// XMLSerializer serializer = new XMLSerializer(System.out, format);
			// XMLSerializer serializer = new XMLSerializer(byte1, format);

//			serializer.serialize(dom);
			// System.out.println(byte1.toString());

//			Desktop.getDesktop().browse(
//					new File(new File("").getAbsolutePath()
//							+ "/xml-src/com/ist/xml/example/book.xml").toURI());

//		} catch (IOException ie) {
//			ie.printStackTrace();
//		}
	}

	Document dom;

	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 */
	private void createDocument() {

		// get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// create an instance of DOM
			dom = db.newDocument();

		} catch (ParserConfigurationException pce) {
			// dump it
			System.out
					.println("Error while trying to instantiate DocumentBuilder "
							+ pce);
			System.exit(1);
		}

	}

	// public DomXmlForFile() {
	// try {
	// /**
	// * ################################
	// *
	// * Creating an empty XML Document
	// *
	// * ################################
	// */
	// /** We need a Document */
	// DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	// DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
	// Document doc = docBuilder.newDocument();
	// /**
	// * ################################
	// *
	// * Creating the XML tree
	// *
	// * ################################
	// */
	// /** create the root element and add it to the document */
	// Element root = doc.createElement("root");
	// doc.appendChild(root);
	//
	// /** create a comment and put it in the root element */
	// Comment comment = doc.createComment("Just a thought");
	// root.appendChild(comment);
	// /** create child element, add an attribute, and add to root */
	// Element child = doc.createElement("child");
	// child.setAttribute("name", "value");
	// child.setAttribute("age", "45");
	// root.appendChild(child);
	// /** add a text element to the child */
	// Text text = doc
	// .createTextNode("\nFiller, ... I could have had a foo!\n");
	// child.appendChild(text);
	// /** create chChild element, add an attribute, and add to child */
	// Element chChild = doc.createElement("chChild");
	// chChild.setAttribute("name", "value");
	// chChild.setAttribute("age", "45");
	// child.appendChild(chChild);
	// /** add a text element to the chChild */
	// chChild.appendChild(doc
	// .createTextNode("\nFiller, ... I could have had a foo!\n"));
	//
	// /**
	// * ################################
	// *
	// * Output the XML
	// *
	// * ################################
	// */
	//
	// /** set up a transformer */
	// TransformerFactory transfac = TransformerFactory.newInstance();
	// Transformer trans = transfac.newTransformer();
	// trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	// trans.setOutputProperty(OutputKeys.INDENT, "yes");
	//
	// /** create string from xml tree */
	// StringWriter sw = new StringWriter();
	// StreamResult result = new StreamResult(sw);
	// DOMSource source = new DOMSource(doc);
	// trans.transform(source, result);
	// String xmlString = sw.toString();
	//
	// /** print xml */
	// System.out.println("Here's the xml:\n\n" + xmlString);
	//
	// } catch (Exception e) {
	// System.out.println(e);
	// }
	// }

//	private String output() {
//
//		try {
//			/** print */
//			OutputFormat format = new OutputFormat(dom);
//			format.setIndenting(true);
//
//			/**
//			 * to generate output to console use this serializer XMLSerializer
//			 * serializer = new XMLSerializer(System.out, format);
//			 */
//
//			/**
//			 * to generate a file output use fileoutputstream instead of
//			 * system.out
//			 */
//			ByteArrayOutputStream byte1 = new ByteArrayOutputStream();
//			XMLSerializer serializer = new XMLSerializer(byte1, format);
//			serializer.serialize(dom);
//			System.out.println(byte1.toString());
//			return byte1.toString();
//		} catch (IOException ie) {
//			ie.printStackTrace();
//		}
//		return "<root/>";
//	}

	@Deprecated
	private String getStringFromDocument(Document doc) {
		try {
			// DOMSource domSource = new DOMSource(doc);
			// StringWriter writer = new StringWriter();
			// StreamResult result = new StreamResult(writer);
			// TransformerFactory tf = TransformerFactory.newInstance();
			// Transformer transformer = tf.newTransformer();
			// transformer.transform(domSource, result);
			// return writer.toString();

			// XPath to retrieve the content of the <version>/<description> tag
			// XPath xpath = XPathFactory.newInstance().newXPath();
			// XPathExpression expr = xpath.compile("//root");
			// Node description = (Node)expr.evaluate(doc, XPathConstants.NODE);
			// System.out.println("description: " +
			// description.getTextContent());
			// return description.getTextContent();

			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.METHOD, "xml");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			// trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
			// Integer.toString(2));

			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc.getDocumentElement());

			trans.transform(source, result);
			String xmlString = sw.toString();
			return xmlString;
		} catch (Throwable ex) {
			ex.printStackTrace();
			return null;
		}
	}
}