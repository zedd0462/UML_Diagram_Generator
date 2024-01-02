package org.mql.java.umlgen.models;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;

@ComplexElement("modifier")
public class ModifierModel implements UMLModelEntity {
	
	private String name;
	private String value;
	
	public ModifierModel(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}
	
	@SimpleElement(value="name", order=1)
	public String getName() {
		return name;
	}
	
	@SimpleElement(value="value", order=2)
	public String getValue() {
		return value;
	}
	
	public static List<ModifierModel> getModifiers(int modifiers){
		List<ModifierModel> modifiersList = new Vector<ModifierModel>();
		if(Modifier.isPublic(modifiers)) {
			modifiersList.add(new ModifierModel("access", "public"));
		}
		else if(Modifier.isProtected(modifiers)) {
			modifiersList.add(new ModifierModel("access", "protected"));
		}
		else if(Modifier.isPrivate(modifiers)) {
			modifiersList.add(new ModifierModel("access", "private"));
		}else {
			modifiersList.add(new ModifierModel("access", "default"));
		}
		if(Modifier.isStatic(modifiers)) {
			modifiersList.add(new ModifierModel("static", "true"));
		}else {
			modifiersList.add(new ModifierModel("static", "false"));
		}
		if(Modifier.isAbstract(modifiers)) {
			modifiersList.add(new ModifierModel("abstract", "true"));
		}else {
			modifiersList.add(new ModifierModel("abstract", "false"));
		}
		if(Modifier.isTransient(modifiers)) {
			modifiersList.add(new ModifierModel("transient", "true"));
		}else{
			modifiersList.add(new ModifierModel("transient", "false"));
		}
		if(Modifier.isSynchronized(modifiers)) {
			modifiersList.add(new ModifierModel("synchronized", "true"));
		}else {
			modifiersList.add(new ModifierModel("synchronized", "false"));
		}
		if(Modifier.isVolatile(modifiers)) {
			modifiersList.add(new ModifierModel("volatile", "true"));
		}else{
			modifiersList.add(new ModifierModel("volatile", "false"));
		}
		if(Modifier.isFinal(modifiers)) {
			modifiersList.add(new ModifierModel("final", "true"));
		}else{
			modifiersList.add(new ModifierModel("final", "false"));
		}
		if(Modifier.isNative(modifiers)) {
			modifiersList.add(new ModifierModel("native", "true"));
		}else{
			modifiersList.add(new ModifierModel("native", "false"));
		}

		if(Modifier.isStrict(modifiers)) {
			modifiersList.add(new ModifierModel("strict", "true"));
		}else{
			modifiersList.add(new ModifierModel("strict", "false"));
		}
		return modifiersList;
	}

}
