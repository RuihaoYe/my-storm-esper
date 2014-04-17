package com.quare.kyle.stormesper;

import java.io.Serializable;

import backtype.storm.tuple.Fields;

public class EventTypeDescriptor implements Serializable{
	private final String name;
	private final Fields fields;
	private final String streamId;
	
	public EventTypeDescriptor(String name, String[] fields, String streamId) {
		this.name = name;
		this.fields = new Fields(fields);
		this.streamId = streamId;
	}

	public String getName() {
		return name;
	}

	public Fields getFields() {
		return fields;
	}

	public String getStreamId() {
		return streamId;
	}
	
	
}
