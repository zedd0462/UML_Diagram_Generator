package org.mql.java.umlgen.xml.parsers;

import org.mql.java.umlgen.models.*;

/**
 * Provides the method parse that takes path to xml files and generates
 * corresponding models.
 */
public class ModelsParser {
	
	private ProjectContext projectContext;

	public ModelsParser() {
		
	}
	
	public ProjectModel parse(String filePath) {
		XMLNode root = new XMLNode(filePath);
		XMLNode projectNode = root.getChild("project");
		ProjectModel projectModel = generateProject(projectNode);
		return projectModel;
	}
	
	public ProjectModel generateProject(XMLNode projectNode) {
		String name = projectNode.getChild("name").getValue();
		String path = projectNode.getChild("path").getValue();
		ProjectModel project = ProjectModel.getInstanceForParser(name, path);
		XMLNode[] packages = projectNode.getChild("packages").getChildren();
		projectContext = project.getProjectContext();
		for (XMLNode child : packages) {
			if(child.isNamed("package")) {
				PackageModel newPackage = generatePackage(child);
				project.addPackage(newPackage);
			}
		}
		return project;
	}
	
	public PackageModel generatePackage(XMLNode packageNode) {
		String name = packageNode.getChild("name").getValue();
		String path = packageNode.getChild("path").getValue();
		PackageModel newPackage = new PackageModel(projectContext, name, path);
		XMLNode[] packages		= packageNode.getChild("packages").getChildren();
		XMLNode[] classes		= packageNode.getChild("classes").getChildren();
		XMLNode[] interfaces	= packageNode.getChild("interfaces").getChildren();
		XMLNode[] annotations	= packageNode.getChild("annotations").getChildren();
		for (XMLNode child : packages) {
			if(child.isNamed("package")) {
				PackageModel subPackage  = generatePackage(child);
				newPackage.addPackage(subPackage);
			}
		}
		for (XMLNode child : classes) {
			if(child.isNamed("class")) {
				ClassModel newClass = generateClass(child);
				newPackage.addClass(newClass);
			}
		}
		for (XMLNode child : interfaces) {
			if(child.isNamed("interface")) {
				InterfaceModel newInterface = generateInterface(child);
				newPackage.addInterface(newInterface);
			}
		}
		for (XMLNode child : annotations) {
			if(child.isNamed("annotation")) {
				AnnotationModel newAnnotation = generateAnnotation(child);
				newPackage.addAnnotation(newAnnotation);
			}
		}
		return newPackage;
	}
	
	public ClassModel generateClass(XMLNode classNode) {
		String name = classNode.getChild("name").getValue();
		int modifiers = classNode.getChild("modifiers").getIntValue();
		ClassModel newClass = new ClassModel(projectContext, name, modifiers);
		XMLNode[] constructors	= classNode.getChild("constructors").getChildren();
		XMLNode[] fields		= classNode.getChild("fields").getChildren();
		XMLNode[] methods		= classNode.getChild("methods").getChildren();
		XMLNode[] relations		= classNode.getChild("relations").getChildren();
		for (XMLNode child : constructors) {
			if(child.isNamed("constructor")) {
				ConstructorModel newConstructor = generateConstructor(child);
				newClass.addConstructor(newConstructor);
			}
		}
		for (XMLNode child : fields) {
			if(child.isNamed("field")) {
				FieldModel newField = generateField(child);
				newClass.addField(newField);
			}
		}
		for (XMLNode child : methods) {
			if(child.isNamed("method")) {
				MethodModel newMethod = generateMethod(child);
				newClass.addMethod(newMethod);
			}
		}
		for (XMLNode child : relations){
			if(child.isNamed("relation")) {
				RelationModel newRelation = generateRelation(child);
				newClass.addRelation(newRelation);
			}
		}
		return newClass;
	}
	
	public InterfaceModel generateInterface(XMLNode interfaceNode) {
		String name = interfaceNode.getChild("name").getValue();
		int modifiers = interfaceNode.getChild("modifiers").getIntValue();
		InterfaceModel newInterface = new InterfaceModel(projectContext, name, modifiers);
		XMLNode[] methods		= interfaceNode.getChild("methods").getChildren();
		XMLNode[] relations		= interfaceNode.getChild("relations").getChildren();
		XMLNode[] fields		= interfaceNode.getChild("fields").getChildren();
		for (XMLNode child : methods) {
			if(child.isNamed("method")) {
				MethodModel newMethod = generateMethod(child);
				newInterface.addMethod(newMethod);
			}
		}
		for (XMLNode child : relations){
			if(child.isNamed("relation")) {
				RelationModel newRelation = generateRelation(child);
				newInterface.addRelation(newRelation);
			}
		}
		for (XMLNode child : fields) {
			if(child.isNamed("field")) {
				FieldModel newField = generateField(child);
				newInterface.addField(newField);
			}
		}
		return newInterface;
	}
	
	public AnnotationModel generateAnnotation(XMLNode annotationNode) {
		String name = annotationNode.getChild("name").getValue();
		int modifiers = annotationNode.getChild("modifiers").getIntValue();
		AnnotationModel newAnnotation = new AnnotationModel(projectContext, name, modifiers);
		XMLNode[] methods		= annotationNode.getChild("methods").getChildren();
		XMLNode[] relations		= annotationNode.getChild("relations").getChildren();
		for (XMLNode child : methods) {
			if(child.isNamed("method")) {
				MethodModel newMethod = generateMethod(child);
				newAnnotation.addMethod(newMethod);
			}
		}
		for (XMLNode child : relations){
			if(child.isNamed("relation")) {
				RelationModel newRelation = generateRelation(child);
				newAnnotation.addRelation(newRelation);
			}
		}
		return newAnnotation;
	}
	
	public ConstructorModel generateConstructor(XMLNode constructorNode) {
		String name = constructorNode.getChild("name").getValue();
		int modifiers = constructorNode.getChild("modifiers").getIntValue();
		ConstructorModel newConstructor = new ConstructorModel(name, modifiers);
		XMLNode[] parameters = constructorNode.getChild("parameters").getChildren();
		for (XMLNode child : parameters) {
			if(child.isNamed("parameter")) {
				ParameterModel newParameter = generateParameter(child);
				newConstructor.addParameter(newParameter);
			}
		}
		return newConstructor;
	}
	
	public FieldModel generateField(XMLNode fieldNode) {
		String name = fieldNode.getChild("name").getValue();
		String returnType = fieldNode.getChild("return").getValue();
		int modifiers = fieldNode.getChild("modifiers").getIntValue();
		FieldModel newField = new FieldModel(name, returnType, modifiers);
		return newField;
	}
	
	public MethodModel generateMethod(XMLNode methodNode) {
		String name = methodNode.getChild("name").getValue();
		String returnType = methodNode.getChild("return").getValue();
		int modifiers = methodNode.getChild("modifiers").getIntValue();
		MethodModel newMethod = new MethodModel(name, modifiers, returnType);
		XMLNode[] parameters = methodNode.getChild("parameters").getChildren();
		for (XMLNode child : parameters) {
			if(child.isNamed("parameter")) {
				ParameterModel newParameter = generateParameter(child);
				newMethod.addParameter(newParameter);
			}
		}
		return newMethod;
	}
	
	public RelationModel generateRelation(XMLNode relationNode) {
		String source = relationNode.getChild("source").getValue();
		String target = relationNode.getChild("target").getValue();
		int type = relationNode.getChild("type").getIntValue();
		String cardinality = relationNode.getChild("cardinality").getValue();
		RelationModel newRelation = new RelationModel(source, target, type, cardinality);
		return newRelation;
	}

	public ParameterModel generateParameter(XMLNode parameterNode) {
		String name = parameterNode.getChild("name").getValue();
		String type = parameterNode.getChild("type").getValue();
		ParameterModel newParameter = new ParameterModel(name, type);
		return newParameter;
	}

}
