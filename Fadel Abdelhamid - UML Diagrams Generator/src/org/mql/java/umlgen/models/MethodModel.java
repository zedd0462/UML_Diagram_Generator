package org.mql.java.umlgen.models;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;



@ComplexElement("method")
public class MethodModel implements UMLModelEntity{
	
	//private AnnotationModel[] annotations;
	private List<ParameterModel> parameters;
	private List<ModiferModel> modifiers;
	private String returnType;
	private String name;

	public MethodModel(Method method) {
		this.parameters = new Vector<ParameterModel>();
		this.modifiers = new Vector<ModiferModel>();		
		this.name = method.getName();
		this.returnType = method.getReturnType().getName();
		for (Parameter p : method.getParameters()) {
			parameters.add(new ParameterModel(p));
		}
		int m = method.getModifiers();
		//TODO: replace this mess
		if(Modifier.isPublic(m)) {
			modifiers.add(new ModiferModel("access", "public"));
		}
		else if(Modifier.isProtected(m)) {
			modifiers.add(new ModiferModel("access", "protected"));
		}
		else if(Modifier.isPrivate(m)) {
			modifiers.add(new ModiferModel("access", "private"));
		}else {
			modifiers.add(new ModiferModel("access", "package"));
		}
		if(Modifier.isStatic(m)) {
			modifiers.add(new ModiferModel("static", "true"));
		}else {
			modifiers.add(new ModiferModel("static", "false"));
		}
		if(Modifier.isAbstract(m)) {
			modifiers.add(new ModiferModel("abstract", "true"));
		}else {
			modifiers.add(new ModiferModel("abstract", "false"));
		}
		if(Modifier.isTransient(m)) {
			modifiers.add(new ModiferModel("transient", "true"));
		}else{
			modifiers.add(new ModiferModel("transient", "false"));
		}
		if(Modifier.isSynchronized(m)) {
			modifiers.add(new ModiferModel("synchronized", "true"));
		}else {
			modifiers.add(new ModiferModel("synchronized", "false"));
		}
		if(Modifier.isVolatile(m)) {
			modifiers.add(new ModiferModel("volatile", "true"));
		}else{
			modifiers.add(new ModiferModel("volatile", "false"));
		}
		if(Modifier.isFinal(m)) {
			modifiers.add(new ModiferModel("final", "true"));
		}else{
			modifiers.add(new ModiferModel("final", "false"));
		}
		if(Modifier.isNative(m)) {
			modifiers.add(new ModiferModel("native", "true"));
		}else{
			modifiers.add(new ModiferModel("native", "false"));
		}

		if(Modifier.isStrict(m)) {
			modifiers.add(new ModiferModel("strict", "true"));
		}else{
			modifiers.add(new ModiferModel("strict", "false"));
		}
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@ComplexElement("parametres")
	public List<ParameterModel> getParameters() {
		return parameters;
	}

	@ComplexElement("modifiers")
	public List<ModiferModel> getModifiers() {
		return modifiers;
	}
	
	@SimpleElement("modifiers")
	public String getName() {
		return name;
	}
	
	@SimpleElement("return")
	public String getReturnType() {
		return returnType;
	}
	
}
