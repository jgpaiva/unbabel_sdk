package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Tone {
	@JsonProperty
	protected String description;
	@JsonProperty
	protected String name;

	@JsonCreator
	public static Tone fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, Tone.class);
	}

	public String toString() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}