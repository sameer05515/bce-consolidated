//package com.prem.interview.xml;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import com.prem.interview.xml.ProjectConfigConstants.Project;
//import com.prem.interview.xml.ProjectConfigConstants.Status;
//import com.prem.interview.xml.ProjectConfigConstants.Ticket;
//import com.prem.interview.xml.ProjectConfigConstants.TicketHistory;
//import com.prem.interview.xml.ProjectConfigConstants.TicketParentChildRelation;
//
//public class XMLUtilityImpl {
//
//	/**
//	 * --- Made the "xmlFileName" variable non-static, so that code can be used
//	 * for several xml file ---
//	 */
//	public String xmlFileName = "C:/APPLN_SERVERS/apache-tomcat-6.0.35/apache-tomcat-6.0.35/webapps/project-ticketing/xml/project.xml";
//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
//
//	XMLUtility utility = null;
//
//	public XMLUtilityImpl(String xmlFileName)
//			throws ParserConfigurationException, SAXException, IOException {
//		this.xmlFileName = xmlFileName;
//		System.out.println("XML file going to be parsed : " + xmlFileName);
//		utility = XMLUtility.getInstance(xmlFileName);
//	}
//
//	public String moveTicket(String ticketID, String parentTicketID)
//			throws ParserConfigurationException, SAXException, IOException {
//
//		// utility = XMLUtility.getInstance(xmlFileName);
//
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketParentChildRelationshipListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKET_PARENT_CHILD_RELATION_SHIP_LIST,
//				rootNode, 0);
//		Element ticketParentChildRelationshipListElement = utility
//				.getElement(ticketParentChildRelationshipListNode);
//
//		// //delete previous entry for the given ticketID
//		NodeList ticketParentChildRelationshipList = utility.getAllNodesOfType(
//				TicketParentChildRelation.node,
//				ticketParentChildRelationshipListElement);
//		for (int count = 0; count < ticketParentChildRelationshipList
//				.getLength(); count++) {
//			Node ticketParentChildRelationshipNode = ticketParentChildRelationshipList
//					.item(count);
//			Element ticketParentChildRelationshipElement = utility
//					.getElement(ticketParentChildRelationshipNode);
//			if (ticketParentChildRelationshipElement != null) {
//				String ticktId = utility.getAttribute(
//						TicketParentChildRelation.TICKET_ID,
//						ticketParentChildRelationshipElement);
//				if (ticktId != null && ticktId.trim().equals(ticketID)) {
//					ticketParentChildRelationshipListElement
//							.removeChild(ticketParentChildRelationshipElement);
//				}
//			}
//		}
//		// //
//
//		Element newTicketParentChildRelationship = utility
//				.createElement(TicketParentChildRelation.node);
//
//		newTicketParentChildRelationship = utility.setAttribute(
//				TicketParentChildRelation.TICKET_ID,
//				newTicketParentChildRelationship, ticketID, false);
//		newTicketParentChildRelationship = utility.setAttribute(
//				TicketParentChildRelation.PARENT_TICKET_ID,
//				newTicketParentChildRelationship, parentTicketID, false);
//		newTicketParentChildRelationship = utility
//				.setAttribute(TicketParentChildRelation.ADDED_ON,
//						newTicketParentChildRelationship,
//						sdf.format(new Date()), false);
//
//		ticketParentChildRelationshipListElement
//				.appendChild(newTicketParentChildRelationship);
//		rootNode.appendChild(ticketParentChildRelationshipListElement);
//
//		utility.printToFile();
//		return utility.getXMLString();
//	}
//
//	public String updateTicket(String ticketID, String linkedProjectId,
//			String description, String statusId, String title,String priority,String enabled)
//			throws ParserConfigurationException, SAXException, IOException {
//
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKETS_LIST, rootNode, 0);
//		Element ticketListElement = utility.getElement(ticketListNode);
//		Element newTicket = null;
//		// ///////////////
//		NodeList ticketHisList = utility.getAllNodesOfType(Ticket.node,
//				ticketListElement);
//		for (int count = 0; count < ticketHisList.getLength(); count++) {
//			Node ticketNode = ticketHisList.item(count);
//			Element ticketElement = utility
//					.getElement(ticketNode);
//			if (ticketElement != null) {
//				String ticktId = utility.getAttribute(Ticket.ID,
//						ticketElement);
//
//				// String nextTicktHistId = utility.getAttribute(
//				// Ticket.ID,
//				// ticketHistoryElement);
//				if (ticktId != null && ticktId.trim().equals(ticketID)) {
//					newTicket = ticketElement;
//					System.out.println("ticketId : " + ticketID);
//					newTicket = utility.setAttribute(Ticket.LINKED_PROJECT_ID,
//							newTicket, linkedProjectId, false);
//					newTicket = utility
//							.setAttribute(
//									Ticket.TITLE,
//									newTicket,
//									(title != null
//											&& !title.trim().equalsIgnoreCase(
//													"") ? title.trim()
//											: "Title not set"), true);
//					newTicket = utility
//							.setAttribute(
//									Ticket.PRIORITY,
//									newTicket,
//									(priority != null
//											&& !priority.trim().equalsIgnoreCase(
//													"") ? priority.trim()
//											: "10"), true);
//					newTicket = utility
//							.setAttribute(
//									Ticket.ENABLED,
//									newTicket,
//									(enabled != null
//											&& !enabled.trim().equalsIgnoreCase(
//													"") ? enabled.trim()
//											: "false"), true);
//
//					ticketListElement.appendChild(newTicket);
//					rootNode.appendChild(ticketListElement);
//					
//				}
//			}
//		}
//		// //////////////
//
//		
//
//		// --- add ticket history ----
//		saveTicketHistory(ticketID, description, statusId, false);
//
//		// utility.getDom().appendChild(rootNode);
//
//		utility.printToFile();
//		return utility.getXMLString();
//	}
//
//	public String saveTicket(String linkedProjectId, String description,
//			String statusId, String title) throws ParserConfigurationException,
//			SAXException, IOException {
//
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKETS_LIST, rootNode, 0);
//		Element ticketListElement = utility.getElement(ticketListNode);
//		Element newTicket = utility.createElement(Ticket.node);
//		String id = "ticket" + System.currentTimeMillis();
//		System.out.println("ticketId : " + id);
//		newTicket = utility.setAttribute(Ticket.ID, newTicket, id, false);
//		newTicket = utility.setAttribute(Ticket.LINKED_PROJECT_ID, newTicket,
//				linkedProjectId, false);
//		newTicket = utility.setAttribute(
//				Ticket.TITLE,
//				newTicket,
//				(title != null && !title.trim().equalsIgnoreCase("") ? title
//						.trim() : "Title not set"), true);
//		newTicket = utility.setAttribute(Ticket.ADDED_ON, newTicket,
//				sdf.format(new Date()), false);
//
//		ticketListElement.appendChild(newTicket);
//		rootNode.appendChild(ticketListElement);
//
//		// --- add ticket history ----
//		saveTicketHistory(id, description, statusId, false);
//
//		// utility.getDom().appendChild(rootNode);
//
//		utility.printToFile();
//		return utility.getXMLString();
//	}
//
//	public String saveTicketHistory(String ticketId, String description,
//			String statusId, boolean print) throws IOException {
//
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketHistoryListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKET_HISTORY_LIST, rootNode, 0);
//		Element ticketHistoryListElement = utility
//				.getElement(ticketHistoryListNode);
//		NodeList ticketHisList = utility.getAllNodesOfType(TicketHistory.node,
//				ticketHistoryListElement);
//
//		String id = "ticketHistory" + System.currentTimeMillis();
//		System.out.println("ticketId : " + id);
//
//		Element previousTicketHistoryElement = null;
//		for (int count = 0; count < ticketHisList.getLength(); count++) {
//			Node ticketHistoryNode = ticketHisList.item(count);
//			Element ticketHistoryElement = utility
//					.getElement(ticketHistoryNode);
//			if (ticketHistoryElement != null) {
//				String ticktId = utility.getAttribute(
//						TicketHistory.LINKED_TICKET_ID, ticketHistoryElement);
//
//				String nextTicktHistId = utility.getAttribute(
//						TicketHistory.NEXT_TICKET_HISTORY_ID,
//						ticketHistoryElement);
//				if (ticktId != null
//						&& ticktId.trim().equals(ticketId)
//						&& (nextTicktHistId != null && nextTicktHistId.trim()
//								.equals(""))) {
//					previousTicketHistoryElement = ticketHistoryElement;
//					utility.setAttribute(TicketHistory.NEXT_TICKET_HISTORY_ID,
//							previousTicketHistoryElement, id, false);
//				}
//			}
//		}
//
//		Element newTicketHistoryElement = utility
//				.createElement(TicketHistory.node);
//		newTicketHistoryElement = utility.setAttribute(TicketHistory.ID,
//				newTicketHistoryElement, id, false);
//		newTicketHistoryElement = utility.setAttribute(
//				TicketHistory.LINKED_TICKET_ID, newTicketHistoryElement,
//				ticketId, false);
//		newTicketHistoryElement = utility.setAttribute(
//				TicketHistory.PREVIUOS_TICKET_HISTORY_ID,
//				newTicketHistoryElement,
//				((previousTicketHistoryElement != null) ? (utility
//						.getAttribute(TicketHistory.ID,
//								previousTicketHistoryElement)) : id), false);
//		newTicketHistoryElement = utility.setAttribute(
//				TicketHistory.NEXT_TICKET_HISTORY_ID, newTicketHistoryElement,
//				"", true);
//		newTicketHistoryElement = utility.setAttribute(TicketHistory.LOGGED_ON,
//				newTicketHistoryElement, sdf.format(new Date()), false);
//
//		Element descriptionElement = utility
//				.createElement(TicketHistory.DESCRIPTION);
//		descriptionElement = utility.appendText(descriptionElement,
//				description, false);
//		newTicketHistoryElement.appendChild(descriptionElement);
//
//		Element statusElement = utility
//				.createElement(TicketHistory.STATUS_CHANGED_TO);
//		statusElement = utility.appendText(statusElement, statusId, false);
//		newTicketHistoryElement.appendChild(statusElement);
//
//		ticketHistoryListElement.appendChild(newTicketHistoryElement);
//		rootNode.appendChild(ticketHistoryListElement);
//
//		if (print) {
//			utility.printToFile();
//		}
//
//		return utility.getXMLString();
//	}
//
//	// ----------------- start-------------
//
//	public static final HashMap<String, String> UNDEFINED_TICKET = null;
//
//	public HashMap<String, String> getTicketByID(String ticketID) {
//		HashMap<String, String> ticket = UNDEFINED_TICKET;
//		List<HashMap<String, String>> ticketMapList = getAllTickets();
//		for (HashMap<String, String> ticketMap : ticketMapList) {
//			if (ticketMap != null && ticketMap.containsKey(Ticket.ID.getName())) {
//				String id = ticketMap.get(Ticket.ID.getName());
//				if (id != null && (id.equals(ticketID))) {
//					ticket = ticketMap;
//					break;
//				}
//			}
//		}
//		return ((ticket != null) ? ticket : UNDEFINED_TICKET);
//	}
//
//	public List<HashMap<String, String>> getAllTickets() {
//
//		List<HashMap<String, String>> ticketMapList = new ArrayList<HashMap<String, String>>();
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKETS_LIST, rootNode, 0);
//		Element ticketListElement = utility.getElement(ticketListNode);
//		NodeList ticketNodeList = utility.getAllNodesOfType(Ticket.node,
//				ticketListElement);
//		for (int count = 0; count < ticketNodeList.getLength(); count++) {
//			Node ticketNode = ticketNodeList.item(count);
//			Element ticketElement = utility.getElement(ticketNode);
//			if (ticketElement != null) {
//
//				HashMap<String, String> ticketMap = new HashMap<String, String>();
//
//				// ------------Ticket.ID---
//				String value = utility.getAttribute(Ticket.ID, ticketElement);
//				ticketMap.put(Ticket.ID.getName(), value);
//
//				// ------------Ticket.ADDED_ON---
//				value = utility.getAttribute(Ticket.ADDED_ON, ticketElement);
//				ticketMap.put(Ticket.ADDED_ON.getName(), value);
//
//				// ------------Ticket.LINKED_PROJECT_ID---
//				value = utility.getAttribute(Ticket.LINKED_PROJECT_ID,
//						ticketElement);
//				ticketMap.put(Ticket.LINKED_PROJECT_ID.getName(), value);
//
//				ticketMapList.add(ticketMap);
//			}
//		}
//		return ticketMapList;
//
//	}
//
//	public static final String UNDEFINED_STATUS = "undefined-status";
//
//	public String getStatusByID(String statusID) {
//		String status = UNDEFINED_STATUS;
//		List<HashMap<String, String>> statusMapList = getAllStatus();
//		for (HashMap<String, String> statusMap : statusMapList) {
//			if (statusMap != null && statusMap.containsKey(Status.ID.getName())) {
//				String id = statusMap.get(Status.ID.getName());
//				if (id != null && (id.equals(statusID))) {
//					status = statusMap.get(Status.LABEL.getName());
//					break;
//				}
//			}
//		}
//		return ((status != null && !status.equalsIgnoreCase("")) ? status
//				: UNDEFINED_STATUS);
//	}
//
//	public static final String UNDEFINED_PROJECT = "undefined-project";
//
//	public String getProjectByID(String projectID) {
//		String project = UNDEFINED_PROJECT;
//		List<HashMap<String, String>> projectMapList = getAllProjects();
//		for (HashMap<String, String> projectMap : projectMapList) {
//			if (projectMap != null
//					&& projectMap.containsKey(Project.ID.getName())) {
//				String id = projectMap.get(Project.ID.getName());
//				if (id != null && (id.equals(projectID))) {
//					project = projectMap.get(Project.LABEL.getName());
//					break;
//				}
//			}
//		}
//		return ((project != null && !project.equalsIgnoreCase("")) ? project
//				: UNDEFINED_PROJECT);
//	}
//
//	public List<HashMap<String, String>> getAllStatus() {
//		List<HashMap<String, String>> statusMapList = new ArrayList<HashMap<String, String>>();
//		Element rootNode = utility.getRootNodeElement();
//		Node statusListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.STATUS_LIST, rootNode, 0);
//		Element statusListElement = utility.getElement(statusListNode);
//		NodeList statusNodeList = utility.getAllNodesOfType(Status.node,
//				statusListElement);
//		for (int count = 0; count < statusNodeList.getLength(); count++) {
//			Node statusNode = statusNodeList.item(count);
//			Element statusElement = utility.getElement(statusNode);
//			if (statusElement != null) {
//
//				HashMap<String, String> statusMap = new HashMap<String, String>();
//
//				// ------------Status.ID---
//				String value = utility.getAttribute(Status.ID, statusElement);
//				statusMap.put(Status.ID.getName(), value);
//
//				// ------------Status.ADDED_ON---
//				value = utility.getAttribute(Status.ADDED_ON, statusElement);
//				statusMap.put(Status.ADDED_ON.getName(), value);
//
//				// ------------Status.LABEL---
//				value = utility.getAttribute(Status.LABEL, statusElement);
//				statusMap.put(Status.LABEL.getName(), value);
//
//				statusMapList.add(statusMap);
//			}
//		}
//		return statusMapList;
//	}
//
//	public List<HashMap<String, String>> getAllProjects() {
//		List<HashMap<String, String>> projectMapList = new ArrayList<HashMap<String, String>>();
//		Element rootNode = utility.getRootNodeElement();
//		Node projectListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.PROJECT_LIST, rootNode, 0);
//		Element projectListElement = utility.getElement(projectListNode);
//		NodeList projectNodeList = utility.getAllNodesOfType(Project.node,
//				projectListElement);
//		for (int count = 0; count < projectNodeList.getLength(); count++) {
//			Node projectNode = projectNodeList.item(count);
//			Element projectElement = utility.getElement(projectNode);
//			if (projectElement != null) {
//
//				HashMap<String, String> projectMap = new HashMap<String, String>();
//
//				// ------------Project.ID---
//				String value = utility.getAttribute(Project.ID, projectElement);
//				projectMap.put(Project.ID.getName(), value);
//
//				// ------------Project.ADDED_ON---
//				value = utility.getAttribute(Project.ADDED_ON, projectElement);
//				projectMap.put(Project.ADDED_ON.getName(), value);
//
//				// ------------Project.LABEL---
//				value = utility.getAttribute(Project.LABEL, projectElement);
//				projectMap.put(Project.LABEL.getName(), value);
//
//				projectMapList.add(projectMap);
//			}
//		}
//		return projectMapList;
//	}
//
//	// -----------------end----------------
//
//	public List<HashMap<String, String>> getAllDescription() {
//		List<HashMap<String, String>> descSet = new ArrayList<HashMap<String, String>>();
//		Element rootNode = utility.getRootNodeElement();
//		Node ticketHistoryListNode = utility.getNthNodeOfType(
//				ProjectConfigConstants.TICKET_HISTORY_LIST, rootNode, 0);
//		Element ticketHistoryListElement = utility
//				.getElement(ticketHistoryListNode);
//		NodeList ticketHisList = utility.getAllNodesOfType(TicketHistory.node,
//				ticketHistoryListElement);
//
//		for (int count = 0; count < ticketHisList.getLength(); count++) {
//			Node javaServiceNode = ticketHisList.item(count);
//			Element ticketHistoryElement = utility.getElement(javaServiceNode);
//			if (ticketHistoryElement != null) {
//
//				HashMap<String, String> ticketHistoryMap = new HashMap<String, String>();
//
//				// ------------TicketHistory.LINKED_TICKET_ID---
//				String value = utility.getAttribute(
//						TicketHistory.LINKED_TICKET_ID, ticketHistoryElement);
//				ticketHistoryMap.put(TicketHistory.LINKED_TICKET_ID.getName(),
//						value);
//
//				// ------------TicketHistory.LOGGED_ON---
//				value = utility.getAttribute(TicketHistory.LOGGED_ON,
//						ticketHistoryElement);
//				ticketHistoryMap.put(TicketHistory.LOGGED_ON.getName(), value);
//
//				// ------------TicketHistory.ID---
//				value = utility.getAttribute(TicketHistory.ID,
//						ticketHistoryElement);
//				ticketHistoryMap.put(TicketHistory.ID.getName(), value);
//
//				// ------------TicketHistory.NEXT_TICKET_HISTORY_ID---
//				value = utility.getAttribute(
//						TicketHistory.NEXT_TICKET_HISTORY_ID,
//						ticketHistoryElement);
//				ticketHistoryMap.put(
//						TicketHistory.NEXT_TICKET_HISTORY_ID.getName(), value);
//
//				// ------------TicketHistory.PREVIUOS_TICKET_HISTORY_ID---
//				value = utility.getAttribute(
//						TicketHistory.PREVIUOS_TICKET_HISTORY_ID,
//						ticketHistoryElement);
//				ticketHistoryMap.put(
//						TicketHistory.PREVIUOS_TICKET_HISTORY_ID.getName(),
//						value);
//
//				// ------------TicketHistory.STATUS_CHANGED_TO---
//				Node ticketHistoryDescNode = utility.getNthNodeOfType(
//						TicketHistory.STATUS_CHANGED_TO, ticketHistoryElement,
//						0);
//				Element ticketHistoryDescElement = utility
//						.getElement(ticketHistoryDescNode);
//				// /
//
//				ticketHistoryMap.put(TicketHistory.STATUS_CHANGED_TO.getName(),
//						ticketHistoryDescElement.getTextContent());
//
//				// ------------TicketHistory.DESCRIPTION---
//				ticketHistoryDescNode = utility.getNthNodeOfType(
//						TicketHistory.DESCRIPTION, ticketHistoryElement, 0);
//				ticketHistoryDescElement = utility
//						.getElement(ticketHistoryDescNode);
//				// /
//
//				ticketHistoryMap.put(TicketHistory.DESCRIPTION.getName(),
//						ticketHistoryDescElement.getTextContent());
//
//				descSet.add(ticketHistoryMap);
//			}
//		}
//
//		return descSet;
//	}
//
//	// /----new part start---------------------------------------
//	public HashMap<String, List<HashMap<String, String>>> getAllTicketHistorySortedByTicketID() {
//		HashMap<String, List<HashMap<String, String>>> descMap = new HashMap<String, List<HashMap<String, String>>>();
//
//		List<HashMap<String, String>> list = getAllDescription();
//		for (int i = list.size() - 1; i >= 0; i--) {
//			HashMap<String, String> mapObject = list.get(i);
//			if (mapObject.keySet() != null && mapObject.keySet().size() > 0) {
//				String linkedTicketID = getStringValue(mapObject,
//						TicketHistory.LINKED_TICKET_ID.getName());
//
//				List<HashMap<String, String>> ticketHistoryList = getMapValue(
//						descMap, linkedTicketID);
//				ticketHistoryList.add(mapObject);
//				descMap.put(linkedTicketID, ticketHistoryList);
//
//			}
//		}
//
//		return descMap;
//	}
//
//	public List<List<HashMap<String, String>>> getAllTicketHistorySortedByLatestUpdate() {
//		HashMap<String, List<HashMap<String, String>>> descMap = getAllTicketHistorySortedByTicketID();
//
//		List<List<HashMap<String, String>>> desc = new ArrayList<List<HashMap<String, String>>>();
//		if (descMap.keySet() != null && descMap.keySet().size() > 0) {
//			for (String descMapKey : descMap.keySet()) {
//
//				List<HashMap<String, String>> list = XMLUtilityImpl
//						.getMapValue(descMap, descMapKey);
//				desc.add(list);
//			}
//		}
//
//		Collections.sort(desc, new Comparator<List<HashMap<String, String>>>() {
//			public int compare(List<HashMap<String, String>> o1,
//					List<HashMap<String, String>> o2) {
//				// /--- sort ticket histories according to the addedOn/loggedOn
//				// date
//				Collections.sort(o1, new Comparator<HashMap<String, String>>() {
//					@Override
//					public int compare(HashMap<String, String> o1,
//							HashMap<String, String> o2) {
//						String linkedTicketID1 = getStringValue(o1,
//								TicketHistory.ID.getName());
//
//						String linkedTicketID2 = getStringValue(o1,
//								TicketHistory.ID.getName());
//						int ticketIDCmp = linkedTicketID1
//								.compareTo(linkedTicketID2);
//						return ticketIDCmp;
//					}
//				});
//				Collections.sort(o2, new Comparator<HashMap<String, String>>() {
//					@Override
//					public int compare(HashMap<String, String> o1,
//							HashMap<String, String> o2) {
//
//						String linkedTicketID1 = getStringValue(o1,
//								TicketHistory.ID.getName());
//
//						String linkedTicketID2 = getStringValue(o1,
//								TicketHistory.ID.getName());
//						int ticketIDCmp = linkedTicketID1
//								.compareTo(linkedTicketID2);
//						return ticketIDCmp;
//
//					}
//				});
//				// /
//				HashMap<String, String> h1 = ((HashMap<String, String>) o1
//						.get(0));
//				HashMap<String, String> h2 = ((HashMap<String, String>) o2
//						.get(0));
//
//				String linkedTicketID1 = getStringValue(h1,
//						TicketHistory.ID.getName());
//
//				String linkedTicketID2 = getStringValue(h2,
//						TicketHistory.ID.getName());
//				int ticketIDCmp = linkedTicketID1.compareTo(linkedTicketID2);
//				return ticketIDCmp;
//			}
//		});
//
//		return desc;
//	}
//
//	public static String getStringValue(HashMap<String, String> mapObject,
//			String key) {
//		String ret = "";
//		if (mapObject.containsKey(key)) {
//			ret = mapObject.get(key);
//			ret = (ret != null) ? ret : "";
//		}
//
//		return ret;
//	}
//
//	public static List<HashMap<String, String>> getMapValue(
//			HashMap<String, List<HashMap<String, String>>> mapObject, String key) {
//		List<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
//		if (mapObject.containsKey(key)) {
//			ret = mapObject.get(key);
//			ret = (ret != null && ret.size() > 0) ? ret
//					: new ArrayList<HashMap<String, String>>();
//		}
//		return ret;
//	}
//
//	// --------new part end -----------------------------------------------
//
//}
