package org.mql.java.umlgen.xml;

import java.util.List;

public class XMLSimpleContent extends XMLElement {
	
	protected String content; 
	

	public XMLSimpleContent(String content) {
		this.attributes = null;
		this.children = null;
		this.elementName = null;
		this.content = content;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void addChildren(XMLElement element) {
		throw new IllegalStateException("Can't innvoke getChildren on simple content");
	}

	@Override
	public void addAttribute(XMLAttribute attribute) {
		throw new IllegalStateException("Can't innvoke getAttribute on simple content");
	}

	@Override
	public String getElementName() {
		throw new IllegalStateException("Can't innvoke getElementName on simple content");
	}

	@Override
	public List<XMLAttribute> getAttributes() {
		throw new IllegalStateException("Can't innvoke getAttributes on simple content");
	}

	@Override
	public List<XMLElement> getChildren() {
		throw new IllegalStateException("Can't innvoke getChildren on simple content");
	}
	
	

	
}
