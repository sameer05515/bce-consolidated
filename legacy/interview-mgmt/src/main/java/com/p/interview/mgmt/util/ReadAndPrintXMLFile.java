package com.p.interview.mgmt.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This java class is being developed for the purpose :-
 * 
 * to save search and fetch previous searches. Currently the deletion of
 * searches is not the primary task
 * 
 * */

public class ReadAndPrintXMLFile {

	private static ReadAndPrintXMLFile thisInstance;
	private static Byte[] justForLock = new Byte[1];

	private ReadAndPrintXMLFile() {
	}

	public static ReadAndPrintXMLFile getInstance() {
		if (thisInstance == null) {
			synchronized (justForLock) {
				if (thisInstance == null) {
					thisInstance = new ReadAndPrintXMLFile();
				}
			}
		}
		return thisInstance;
	}

	public static void main(String argv[]) {
		String fileName = new File(new File(
				"D:/javaEclipseRoot/SampleTestProjects").getAbsolutePath()
				+ "/xml-src/com/ist/xml/parameterschema/book.xml")
				.getAbsolutePath();
		ReadAndPrintXMLFile.getInstance().fetchAllSearches(fileName);
	}

	public void saveSearch(String searchPath) {

	}

	public void fetchAllSearches(String fileName) {
		try {
			Document doc = getDocument(fileName, true);

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is "
					+ doc.getDocumentElement().getNodeName());

			NodeList listOfPersons = doc.getElementsByTagName("person");
			int totalPersons = listOfPersons.getLength();
			System.out.println("Total no of people : " + totalPersons);

			for (int s = 0; s < listOfPersons.getLength(); s++) {

				Node firstPersonNode = listOfPersons.item(s);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstPersonNode;

					// -------
					NodeList firstNameList = firstPersonElement
							.getElementsByTagName("first");
					Element firstNameElement = (Element) firstNameList.item(0);

					NodeList textFNList = firstNameElement.getChildNodes();
					System.out
							.println("First Name : "
									+ ((Node) textFNList.item(0))
											.getNodeValue().trim());

					// -------
					NodeList lastNameList = firstPersonElement
							.getElementsByTagName("last");
					Element lastNameElement = (Element) lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					System.out
							.println("Last Name : "
									+ ((Node) textLNList.item(0))
											.getNodeValue().trim());

					// ----
					NodeList ageList = firstPersonElement
							.getElementsByTagName("age");
					Element ageElement = (Element) ageList.item(0);

					NodeList textAgeList = ageElement.getChildNodes();
					System.out.println("Age : "
							+ ((Node) textAgeList.item(0)).getNodeValue()
									.trim());

					// ------

				}// end of if clause

			}// end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public Document getDocument(String fileName, boolean createIfNotExists)
			throws ParserConfigurationException, SAXException, IOException {

		if (fileName == null || fileName.trim().equalsIgnoreCase("")) {
			return null;
		}
		try {
			File f = new File(fileName);
			if (createIfNotExists) {
				if (!(f.exists())) {
					if (!(f.getParentFile().exists())) {
						f.getParentFile().mkdirs();
					}
					f.createNewFile();
				}
			}
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(f);
			return doc;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
