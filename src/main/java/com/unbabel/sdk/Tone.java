package com.unbabel.sdk;

import org.codehaus.jackson.annotate.JsonProperty;

public class Tone {
   @JsonProperty
	public String description;
   @JsonProperty
	public String name;

	public String toString() {
		return name;
	}
}