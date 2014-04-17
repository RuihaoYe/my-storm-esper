package com.quare.kyle.stormesper;

import java.io.Serializable;

public class StreamId implements Serializable{
	private final String componentId;
	private final String streamId;
	
	public StreamId(String componentId) {
		this(componentId, "default");
	}
	
	public StreamId(String componentId, String streamId) {
		this.componentId = componentId;
		this.streamId = streamId;
	}
	
	public String getComponentId() {
		return componentId;
	}
	
	public String getStreamId() {
		return streamId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((componentId == null) ? 0 : componentId.hashCode());
		result = prime * result
				+ ((streamId == null) ? 0 : streamId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StreamId other = (StreamId) obj;
		if (componentId == null) {
			if (other.componentId != null)
				return false;
		} else if (!componentId.equals(other.componentId))
			return false;
		if (streamId == null) {
			if (other.streamId != null)
				return false;
		} else if (!streamId.equals(other.streamId))
			return false;
		return true;
	}
	
	
}
