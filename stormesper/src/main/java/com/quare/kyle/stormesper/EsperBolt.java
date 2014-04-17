package com.quare.kyle.stormesper;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class EsperBolt extends BaseRichBolt implements UpdateListener {
	private static final long serialVersionUID = 1L;

	public static class Builder {
		protected final EsperBolt bolt;

		public Builder() {
			this(new EsperBolt());
		}

		public Builder(EsperBolt bolt) {
			this.bolt = bolt;
		}

		public InputsBuilder inputs() {
			return new InputsBuilder(bolt);
		}

		public OutputsBuilder outputs() {
			return new OutputsBuilder(bolt);
		}

		public StatementsBuilder statements() {
			return new StatementsBuilder(bolt);
		}

		public EsperBolt build() {
			return bolt;
		}
	}

	public static class InputsBuilder extends Builder {
		private InputsBuilder(EsperBolt bolt) {
			super(bolt);
		}

		public AliasedInputBuilder aliasComponent(String componentId) {
			return new AliasedInputBuilder(bolt, new StreamId(componentId));
		}

		public AliasedInputBuilder aliasStream(String componentId,
				String streamId) {
			return new AliasedInputBuilder(bolt, new StreamId(componentId,
					streamId));
		}
	}

	public static final class AliasedInputBuilder {
		private final EsperBolt bolt;
		private final StreamId streamId;
		private final Map<String, String> fieldTypes;

		private AliasedInputBuilder(EsperBolt bolt, StreamId streamId) {
			this(bolt, streamId, new HashMap<String, String>());
		}

		private AliasedInputBuilder(EsperBolt bolt, StreamId streamId,
				Map<String, String> fieldTypes) {
			this.bolt = bolt;
			this.streamId = streamId;
			this.fieldTypes = fieldTypes;
		}

		public TypedInputBuilder withField(String fieldNames) {
			return new TypedInputBuilder(bolt, streamId, fieldTypes, fieldNames);
		}

		public TypedInputBuilder withFields(String... fieldNames) {
			return new TypedInputBuilder(bolt, streamId, fieldTypes, fieldNames);
		}

		public InputsBuilder toEventType(String name) {
			bolt.addInputAlias(streamId, name, new TupleTypeDescriptor(
					fieldTypes));
			return new InputsBuilder(bolt);
		}
	}

	public static final class TypedInputBuilder {
		private final EsperBolt bolt;
		private final StreamId streamId;
		private final Map<String, String> fieldTypes;
		private final String[] fieldNames;

		public TypedInputBuilder(EsperBolt bolt, StreamId streamId,
				Map<String, String> fieldTypes, String... fieldNames) {
			this.bolt = bolt;
			this.streamId = streamId;
			this.fieldTypes = fieldTypes;
			this.fieldNames = fieldNames;
		}

		/*
		 * ?
		 */
		public AliasedInputBuilder ofType(Class<?> type) {
			for (String fieldName : fieldNames) {
				fieldTypes.put(fieldName, type.getName());
			}
			return new AliasedInputBuilder(bolt, streamId, fieldTypes);
		}
	}

	public static final class OutputsBuilder extends Builder {

		public OutputsBuilder(EsperBolt bolt) {
			super(bolt);
		}

		public OutputStreamBuilder onStream(String streamName) {
			return new OutputStreamBuilder(bolt, streamName);
		}

		public OutputStreamBuilder onDefaultStream() {
			return new OutputStreamBuilder(bolt, "default");
		}

	}

	public static final class OutputStreamBuilder {

		private final EsperBolt bolt;
		private final String streamName;

		public OutputStreamBuilder(EsperBolt bolt, String streamName) {
			this.bolt = bolt;
			this.streamName = streamName;
		}

		public NamedOutputStreamBuilder fromEventType(String name) {
			return new NamedOutputStreamBuilder(bolt, streamName, name);
		}

		public OutputsBuilder emit(String... fields) {
			bolt.setAnonymousOutput(streamName, fields);
			return new OutputsBuilder(bolt);
		}
	}

	public static final class NamedOutputStreamBuilder {

		private final EsperBolt bolt;
		private final String streamName;
		private final String eventTypeName;

		public NamedOutputStreamBuilder(EsperBolt bolt, String streamName,
				String name) {
			this.bolt = bolt;
			this.streamName = streamName;
			this.eventTypeName = name;
		}

		public OutputsBuilder emit(String... fields) {
			bolt.addNamedOutput(streamName, eventTypeName, fields);
			return new OutputsBuilder(bolt);
		}
	}

	public static final class StatementsBuilder extends Builder {

		public StatementsBuilder(EsperBolt bolt) {
			super(bolt);
		}

		public StatementsBuilder add(String statement) {
			bolt.addStatement(statement);
			return this;
		}

	}
	
	private final Map<StreamId, String> inputAliases = new LinkedHashMap<StreamId, String>();
	private final Map<StreamId, TupleTypeDescriptor> tupleTypes = new LinkedHashMap<StreamId, TupleTypeDescriptor>();
	private final Map<String, EventTypeDescriptor> eventTypes = new LinkedHashMap<String, EventTypeDescriptor>();
	private final List<String> statements = new ArrayList<String>();
	// transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。
	private transient EPServiceProvider esperSink;
	private transient EPRuntime runtime;
	private transient EPAdministrator admin;
	private transient OutputCollector collector;
	
	private EsperBolt() {
		
	}

	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		// TODO Auto-generated method stub

	}

	public void addStatement(String statement) {
		// TODO Auto-generated method stub

	}

	public void addNamedOutput(String streamName, String eventTypeName,
			String[] fields) {
		// TODO Auto-generated method stub

	}

	public void setAnonymousOutput(String streamName, String[] fields) {
		// TODO Auto-generated method stub

	}

	public void addInputAlias(StreamId streamId, String name,
			TupleTypeDescriptor tupleTypeDescriptor) {
		// TODO Auto-generated method stub

	}

	public void execute(Tuple input) {
		// TODO Auto-generated method stub

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		// TODO Auto-generated method stub

	}
}
