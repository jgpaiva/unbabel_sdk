package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Language {
	@JsonProperty("shortname")
	protected String shortName;
	@JsonProperty
	protected String name;

	@JsonCreator
	public static Language fromJSON(String obj_json) {
		return Utils.objectFromJSON(obj_json, Language.class);
	}

	public String toString() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}