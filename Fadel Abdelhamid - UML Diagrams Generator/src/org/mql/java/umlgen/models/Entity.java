package org.mql.java.umlgen.models;

/**
 * Represents an Entity that can be represented. (Class, Interface ...etc).
 */
public interface Entity extends Model{
	public String getName();
	public void resolveRelations();
}
