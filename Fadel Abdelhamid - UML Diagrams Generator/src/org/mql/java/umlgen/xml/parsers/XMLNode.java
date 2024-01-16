package org.mql.java.umlgen.xml.parsers;

import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.umlgen.ui.ErrorDialog;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	
	private Node node;
	private XMLNode[] children;

	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(source);
			Node node = document.getFirstChild();
			while(node.getNodeType() != Node.ELEMENT_NODE) {
				node = node.getNextSibling();
			}
			setNode(node);
		} catch (Exception e) {
			System.out.println("Erreur  :" + e.getMessage());
		}
	}
	
	public XMLNode(Node node) {
		super();
		setNode(node);
	}

	public Node getNode() {
		return node;
	}
	
	public String getName() {// Delegate Method
		return node.getNodeName();
	}
	
	public boolean isNamed(String name) {
		return this.getNode().getNodeName().equals(name);
	}

	public void setNode(Node node) {
		this.node = node;
		extractChildren();
	}
	
	private void extractChildren() {
		NodeList list =  node.getChildNodes();
		LinkedList<XMLNode> nodes = new LinkedList<XMLNode>();
		for (int i = 0; i < list.getLength(); i++) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE) {
				nodes.add(new XMLNode(list.item(i)));
			}
		}
		children = new XMLNode[nodes.size()];
		nodes.toArray(children);
	}

	public XMLNode[] getChildren() {
		return children;
	}
	
	public XMLNode getChild(String name) {
		for (XMLNode child : children) {
			if (child.isNamed(name)) {
				return child;
			}
		}
		return null;
	}
	
	public String getValue() {
		Node child = node.getFirstChild();
		if (child != null && child.getNodeType() == Node.TEXT_NODE) {
			return child.getNodeValue();
		}
		return "";
	}
	
	public int getIntValue() {
		int resultInt = 0;
		String resultString = getValue();
		try {
			resultInt = Integer.parseInt(resultString);
		} catch (Exception e) {
			e.printStackTrace();
			new ErrorDialog(e.getMessage());
		}
		return resultInt;
	}
	
	public String getAttribute(String name) {
		Node att = this.node.getAttributes().getNamedItem(name);
		if (att == null) return "";
		return att.getNodeValue();
	}
	
	public int getIntAttribute(String name) {
		String s = this.getAttribute(name);
		int value = 0;
		try {
			value = Integer.parseInt(s);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
		return value;
	}
	
	

}
