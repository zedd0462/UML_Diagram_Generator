package org.mql.java.umlgen.models;

import java.util.Map;

import org.mql.java.umlgen.annotations.ComplexElement;
import org.mql.java.umlgen.annotations.SimpleElement;
import org.mql.java.umlgen.xml.XMLElement;
import org.mql.java.umlgen.xml.XMLElementGenerator;


@ComplexElement(value="relation")
public class RelationModel implements UMLModelEntity {
	
	public static Map<Integer, String> relationString = Map.of(
				0, "Association", // 
				1, "Aggregation", // Not yet implemented
				2, "Composition", // Not yet implemented
				3, "Inheritance", //
				4, "Realization", //
				5, "Dependency"	  // Not yet implemented
			);
	
	private RelationEntity source;
	private RelationEntity target;
	private int relationType;
	private String cardinality = "N/A";

	public RelationModel(RelationEntity source, RelationEntity target) {
		this.source = source;
		this.target = target;
		this.relationType = 0; //If not specified it is an association
	}
	
	public RelationModel(RelationEntity source, RelationEntity target, int relationType) {
		this(source, target);
		this.relationType = relationType;
	}

	@Override
	public XMLElement getElementModel(XMLElementGenerator generator) {
		return generator.generate(this);
	}

	@SimpleElement(value="source", order=1)
	public String getSourceClassString() {
		return source.getName();
	}

	@SimpleElement(value="target", order=2)
	public String getTargetClassString() {
		return target.getName();
	}
	
	@SimpleElement(value="type", order=3)
	public String getRelationTypeString() {
		return relationString.get(relationType);
	}
	
	public int getRelationType() {
		return relationType;
	}
	
	@SimpleElement(value="cardinality", order=4)
	public String getCardinality() {
		return cardinality;
	}

	
	

}
