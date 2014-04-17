package com.quare.kyle.stormesper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TupleTypeDescriptor implements Serializable{
	private final Map<String, String> fieldTypes;

	public TupleTypeDescriptor(Map<String, String> fieldTypes) {
		this.fieldTypes = new HashMap<String, String>(fieldTypes);
	}
	
	public String getFieldType(String fieldName) {
		return fieldTypes.get(fieldName);
	}
}
