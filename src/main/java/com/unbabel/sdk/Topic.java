package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Topic {
	@JsonProperty
	protected String name;

	@JsonCreator
	public static Topic fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, Topic.class);
	}

	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}